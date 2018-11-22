package com.hz.learnboot.mongodb.repository.primary;

import com.hz.learnboot.mongodb.domain.primary.City;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * 城市 Repository 接口类
 *
 * @Author hezhao
 * @Time 2018-06-30 23:59
 */
public interface CityRepository extends MongoRepository<City, Long> {

    // 继承了 MongoRepository 会默认实现很多基本的增删改查，省了很多自己写dao层的代码

    // 不需要写实现
    City findByCityName(String cityName);
}
