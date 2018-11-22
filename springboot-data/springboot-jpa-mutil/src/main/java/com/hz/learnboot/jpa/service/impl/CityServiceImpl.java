package com.hz.learnboot.jpa.service.impl;

import com.hz.learnboot.jpa.dao.secondary.CityRepository;
import com.hz.learnboot.jpa.domain.secondary.City;
import com.hz.learnboot.jpa.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hezhao on 2018-07-02 09:07
 */
@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityRepository cityRepository;

    @Override
    public List<City> queryAll() {
        return cityRepository.findAll();
    }

    @Override
    public City findByCityName(String cityName) {
        return cityRepository.findByCityName(cityName);
    }
}
