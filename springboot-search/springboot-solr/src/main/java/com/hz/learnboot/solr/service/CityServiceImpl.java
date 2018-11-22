package com.hz.learnboot.solr.service;

import com.hz.learnboot.solr.domain.City;
import com.hz.learnboot.solr.repository.CityRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author hezhao
 * @Time 2018-07-22 22:37
 */
@Service
public class CityServiceImpl implements CityService {

    @Autowired
    CityRepository cityRepository;

    @Override
    public List<City> findAll() {
        List<City> list = new ArrayList<>();
        cityRepository.findAll().forEach(e -> list.add(e));
        return list;
    }

    @Override
    public List<City> findByCityName(String name) {
        if(StringUtils.isEmpty(name)){
            return new ArrayList<>();
        }
        return cityRepository.findByCityName(name);
    }

    @Override
    public Page<City> query(String queryString, Pageable pageable) {
        return cityRepository.findByDescriptionContaining(queryString, pageable);
    }

    @Override
    public void save(City city) {
        if(city != null) {
            cityRepository.save(city);
        }
    }
}
