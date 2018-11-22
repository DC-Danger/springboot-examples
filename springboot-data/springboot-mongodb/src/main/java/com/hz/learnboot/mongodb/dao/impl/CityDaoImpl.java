package com.hz.learnboot.mongodb.dao.impl;

import com.hz.learnboot.mongodb.dao.CityDao;
import com.hz.learnboot.mongodb.domain.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hezhao on 2018-07-05 17:54
 */
@Repository
public class CityDaoImpl implements CityDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 查询所有
     * @return
     */
    @Override
    public List<City> findAllCity() {
        return mongoTemplate.findAll(City.class);
    }

    /**
     * 根据主键查询
     * @param id
     * @return
     */
    @Override
    public City findById(Long id) {
        return mongoTemplate.findById(id, City.class);
    }

    /**
     * 根据条件查询单个对象
     * @param cityName
     * @return
     */
    @Override
    public City findByCityName(String cityName) {
        Query query = new Query(Criteria.where("cityName").is(cityName));
        return mongoTemplate.findOne(query, City.class);
    }

    /**
     * 保存对象, 根据Id是否存在来决定是否是新增还是更新
     *
     * 如果集合不存在，会自动创建
     * @param city
     */
    @Override
    public void saveCity(City city) {
        mongoTemplate.save(city);
    }

    /**
     * 根据条件更新对象
     * @param city
     */
    @Override
    public void updateCityByCityName(City city) {
        Query query = new Query(Criteria.where("cityName").is(city.getCityName()));
        Update update = new Update().set("description", city.getDescription()).set("provinceId", city.getProvinceId());

        // 更新查询返回结果集的第一条
        mongoTemplate.updateFirst(query, update, City.class);

        // 更新查询返回结果集的所有
        // mongoTemplate.updateMulti(query, update, City.class);
    }

    /**
     * 删除对象
     * @param id
     */
    @Override
    public void deleteCity(Long id) {
        Query query = new Query(Criteria.where("id").is(id));
        mongoTemplate.remove(query, City.class);
    }
}
