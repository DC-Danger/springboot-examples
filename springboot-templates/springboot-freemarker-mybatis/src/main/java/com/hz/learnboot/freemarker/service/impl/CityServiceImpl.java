package com.hz.learnboot.freemarker.service.impl;

import com.hz.learnboot.freemarker.dao.CityMapper;
import com.hz.learnboot.freemarker.domain.City;
import com.hz.learnboot.freemarker.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by hezhao on 2018-07-04 09:5
 */
@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityMapper cityMapper;

    public List<City> findAllCity(){
        return cityMapper.findAllCity();
    }

    public City findCityById(Long id) {
        return cityMapper.findById(id);
    }

    @Override
    public Long saveCity(City city) {
        return cityMapper.saveCity(city);
    }

    @Override
    public Long updateCity(City city) {
        return cityMapper.updateCity(city);
    }

    @Override
    public Long deleteCity(Long id) {
        return cityMapper.deleteCity(id);
    }

}
