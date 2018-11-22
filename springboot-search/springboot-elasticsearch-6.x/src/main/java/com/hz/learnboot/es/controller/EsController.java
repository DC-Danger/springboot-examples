package com.hz.learnboot.es.controller;

import com.alibaba.fastjson.JSONObject;
import com.hz.learnboot.es.domain.Person;
import com.hz.learnboot.es.util.ElasticsearchUtil;
import com.hz.learnboot.es.util.EsPage;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 测试控制器
 *
 * Created by hezhao on 2018-07-20 18:33
 */
@RestController
@RequestMapping("/es")
public class EsController {

    /** 测试索引 */
    private String indexName = "test_index";

    /** 类型 */
    private String typeName = "external";

    /**
     * http://127.0.0.1:8080/es/createIndex
     * 创建索引
     * @return
     */
    @RequestMapping("/createIndex")
    public String createIndex() {
        if(!ElasticsearchUtil.isIndexExist(indexName)) {
            ElasticsearchUtil.createIndex(indexName);
        } else{
            return "索引已经存在";
        }
        return "索引创建成功";
    }

    /**
     * 插入记录
     * @return
     */
    @RequestMapping("/insertJson")
    public String insertJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", DateFormatUtils.format(new Date(), "yyyyMMddHHmmssSSS"));
        jsonObject.put("age", 25);
        jsonObject.put("name", "j-" + new Random(100).nextInt());
        jsonObject.put("date", new Date());
        String id = ElasticsearchUtil.addData(jsonObject, indexName, typeName, jsonObject.getString("id"));
        return id;
    }

    /**
     * 插入记录
     * @return
     */
    @RequestMapping("/insertModel")
    public String insertModel() {
        Person person = new Person();
        person.setId(DateFormatUtils.format(new Date(), "yyyyMMddHHmmssSSS"));
        person.setName("m-" + new Random(100).nextInt());
        person.setAge(30);
        person.setDate(new Date());
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(person);
        String id = ElasticsearchUtil.addData(jsonObject, indexName, typeName, jsonObject.getString("id"));
        return id;
    }

    /**
     * 删除记录
     * @return
     */
    @RequestMapping("/delete")
    public String delete(String id) {
        if(StringUtils.isNotBlank(id)) {
            ElasticsearchUtil.deleteDataById(indexName, typeName, id);
            return "删除id=" + id;
        } else{
            return "id为空";
        }
    }

    /**
     * 更新数据
     * @return
     */
    @RequestMapping("/update")
    public String update(String id) {
        if(StringUtils.isNotBlank(id)) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", id);
            jsonObject.put("age", 31);
            jsonObject.put("name", "修改");
            jsonObject.put("date", new Date());
            ElasticsearchUtil.updateDataById(jsonObject, indexName, typeName, id);
            return "id=" + id;
        } else{
            return "id为空";
        }
    }

    /**
     * 获取数据
     * http://127.0.0.1:8080/es/getData?id=2018-04-25%2016:33:44
     * @param id
     * @return
     */
    @RequestMapping("/getData")
    public String getData(String id){
        if(StringUtils.isNotBlank(id)) {
            Map<String, Object> map = ElasticsearchUtil.searchDataById(indexName, typeName,id,null);
            return JSONObject.toJSONString(map);
        } else{
            return "id为空";
        }
    }

    /**
     * 查询数据
     * 模糊查询
     * @return
     */
    @RequestMapping("/queryMatchData")
    public String queryMatchData() {
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        boolean matchPhrase = false;
        if (matchPhrase == Boolean.TRUE) {
            boolQuery.must(QueryBuilders.matchPhraseQuery("name", "修"));
        } else {
            boolQuery.must(QueryBuilders.matchQuery("name", "修"));
        }
        List<Map<String, Object>> list = ElasticsearchUtil.searchListData(indexName, typeName, boolQuery, 10, null, null, null);
        return JSONObject.toJSONString(list);
    }

    /**
     * 通配符查询数据
     * 通配符查询 ?用来匹配1个任意字符，*用来匹配零个或者多个字符
     * @return
     */
    @RequestMapping("/queryWildcardData")
    public String queryWildcardData() {
        QueryBuilder queryBuilder = QueryBuilders.wildcardQuery("name.keyword", "j-*466");
        List<Map<String, Object>> list = ElasticsearchUtil.searchListData(indexName, typeName, queryBuilder, 10, null, null, null);
        return JSONObject.toJSONString(list);
    }

    /**
     * 正则查询
     * @return
     */
    @RequestMapping("/queryRegexpData")
    public String queryRegexpData() {
        QueryBuilder queryBuilder = QueryBuilders.regexpQuery("name.keyword", "j--[0-9]{1,11}");
        List<Map<String, Object>> list = ElasticsearchUtil.searchListData(indexName, typeName, queryBuilder, 10, null, null, null);
        return JSONObject.toJSONString(list);
    }

    /**
     * 查询数字范围数据
     * @return
     */
    @RequestMapping("/queryIntRangeData")
    public String queryIntRangeData() {
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        boolQuery.must(QueryBuilders.rangeQuery("age").from(21).to(25));
        List<Map<String, Object>> list = ElasticsearchUtil.searchListData(indexName, typeName, boolQuery, 10, null, null, null);
        return JSONObject.toJSONString(list);
    }

    /**
     * 查询日期范围数据
     * @return
     */
    @RequestMapping("/queryDateRangeData")
    public String queryDateRangeData() {
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        boolQuery.must(QueryBuilders.rangeQuery("date").from("2018-04-25T08:33:44.840Z")
                .to("2018-04-25T10:03:08.081Z"));
        List<Map<String, Object>> list = ElasticsearchUtil.searchListData(indexName, typeName, boolQuery, 10, null, null, null);
        return JSONObject.toJSONString(list);
    }

    /**
     * 查询分页
     * @param startPage   第几条记录开始
     *                    从0开始
     *                    第1页 ：http://127.0.0.1:8080/es/queryPage?startPage=0&pageSize=2
     *                    第2页 ：http://127.0.0.1:8080/es/queryPage?startPage=2&pageSize=2
     * @param pageSize    每页大小
     * @return
     */
    @RequestMapping("/queryPage")
    public String queryPage(String startPage, String pageSize){
        if(StringUtils.isNotBlank(startPage) && StringUtils.isNotBlank(pageSize)) {
            BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
            boolQuery.must(QueryBuilders.rangeQuery("date").from("2018-04-25T08:33:44.840Z")
                    .to("2019-04-25T10:03:08.081Z"));
            EsPage list = ElasticsearchUtil.searchDataPage(indexName, typeName, Integer.parseInt(startPage), Integer.parseInt(pageSize), boolQuery, null, null, null);
            return JSONObject.toJSONString(list);
        } else{
            return  "startPage或者pageSize缺失";
        }
    }

}
