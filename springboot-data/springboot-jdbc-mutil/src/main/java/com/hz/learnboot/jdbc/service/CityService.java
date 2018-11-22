package com.hz.learnboot.jdbc.service;

import com.hz.learnboot.jdbc.domain.City;

import java.util.List;

/** CityService
 * Created by hezhao on 2018-07-04 09:45
 */
public interface CityService {
    List<City> queryAll();

    int insert(City city);
}
