package com.hz.learnboot.es.service.impl;

import com.hz.learnboot.es.domain.City;
import com.hz.learnboot.es.repository.CityRepository;
import com.hz.learnboot.es.service.CityService;
import org.elasticsearch.common.lucene.search.function.FiltersFunctionScoreQuery;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 城市 ES 业务逻辑实现类
 *
 * Created by hezhao on 2018-07-20 12:09
 */
@Service
public class CityServiceImpl implements CityService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CityServiceImpl.class);

    /** 每页数量 */
    private static final Integer PAGE_SIZE = 10;
    /** 默认当前页码 */
    private static final Integer DEFAULT_PAGE_NUMBER = 0;
    /** 默认分页对象 */
    private static final Pageable DEFAULT_PAGEABLE = PageRequest.of(DEFAULT_PAGE_NUMBER, PAGE_SIZE);
    /** 由于无相关性的分值默认为 1 ，设置权重分最小值为 10 */
    private static final Float  MIN_SCORE = 10.0F;

    // ES 操作类
    @Autowired
    private CityRepository cityRepository;

    @Override
    public Long saveCity(City city) {
        City cityResult = cityRepository.save(city);
        return cityResult.getId();
    }

    @Override
    public List<City> findByDescriptionAndScore(String description, Integer score) {
        return cityRepository.findByDescriptionAndScore(description, score);
    }

    @Override
    public List<City> findByDescriptionOrScore(String description, Integer score) {
        return cityRepository.findByDescriptionOrScore(description, score);
    }

    @Override
    public List<City> findByDescription(String description) {
        return cityRepository.findByDescription(description, DEFAULT_PAGEABLE).getContent();
    }

    @Override
    public List<City> findByDescriptionNot(String description) {
        return cityRepository.findByDescriptionNot(description, DEFAULT_PAGEABLE).getContent();
    }

    @Override
    public List<City> findByDescriptionLike(String description) {
        return cityRepository.findByDescriptionLike(description, DEFAULT_PAGEABLE).getContent();
    }

    @Override
    public List<City> searchCity(Integer pageNumber, Integer pageSize, String searchContent) {

        // 校验分页参数
        if (pageSize == null || pageSize <= 0) {
            pageSize = PAGE_SIZE;
        }

        if (pageNumber == null || pageNumber < DEFAULT_PAGE_NUMBER) {
            pageNumber = DEFAULT_PAGE_NUMBER;
        }

        LOGGER.info("\n searchCity: searchContent [" + searchContent + "] \n ");

        // 构建搜索查询
        SearchQuery searchQuery = getCitySearchQuery(pageNumber,pageSize,searchContent);

        LOGGER.info("\n searchCity: searchContent [" + searchContent + "] \n DSL  = \n " + searchQuery.getQuery().toString());

        Page<City> cityPage = cityRepository.search(searchQuery);
        return cityPage.getContent();
    }

    /**
     * 根据搜索词构造搜索查询语句
     *
     * 代码流程：
     *      - 权重分查询
     *      - 短语匹配
     *      - 设置权重分最小值
     *      - 设置分页参数
     *
     * @param pageNumber 当前页码
     * @param pageSize 每页大小
     * @param searchContent 搜索内容
     * @return
     */
    private SearchQuery getCitySearchQuery(Integer pageNumber, Integer pageSize,String searchContent) {
        // 短语匹配到的搜索词，求和模式累加权重分
        // 权重分查询 https://www.elastic.co/guide/cn/elasticsearch/guide/current/function-score-query.html
        //   - 短语匹配 https://www.elastic.co/guide/cn/elasticsearch/guide/current/phrase-matching.html
        //   - 字段对应权重分设置，可以优化成 enum
        //   - 由于无相关性的分值默认为 1 ，设置权重分最小值为 10

        // 5.x后QueryBuilder.add方法没有了 - https://elasticsearch.cn/question/2312
        FunctionScoreQueryBuilder.FilterFunctionBuilder[] builders = {
                new FunctionScoreQueryBuilder.FilterFunctionBuilder(QueryBuilders.matchPhraseQuery("name", searchContent),ScoreFunctionBuilders.weightFactorFunction(1000)),
                new FunctionScoreQueryBuilder.FilterFunctionBuilder(QueryBuilders.matchPhraseQuery("description", searchContent),ScoreFunctionBuilders.weightFactorFunction(500))
        };

        // ScoreMode.SUM 权重分求和模式
        FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery(builders)
                .scoreMode(FiltersFunctionScoreQuery.ScoreMode.SUM).setMinScore(MIN_SCORE);

        // 分页参数
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return new NativeSearchQueryBuilder()
                .withPageable(pageable)
                .withQuery(functionScoreQueryBuilder).build();
    }

}
