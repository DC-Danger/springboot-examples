package com.hz.learnboot.jpa.service;

import com.hz.learnboot.jpa.domain.secondary.City;

import java.util.List;

/**
 * @Author hezhao
 * @Time 2018-07-01 21:35
 */
public interface CityService {
    List<City> queryAll();

    City findByCityName(String cityName);
}
