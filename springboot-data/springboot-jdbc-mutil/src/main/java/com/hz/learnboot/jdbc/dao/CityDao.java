package com.hz.learnboot.jdbc.dao;

import com.hz.learnboot.jdbc.domain.City;

import java.util.List;

/** 城市Dao
 * Created by hezhao on 2018-07-04 09:35
 */
public interface CityDao {
    List<City> queryAll();

    int insert(City city);
}
