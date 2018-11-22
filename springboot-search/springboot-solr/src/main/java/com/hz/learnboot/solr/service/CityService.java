package com.hz.learnboot.solr.service;

import com.hz.learnboot.solr.domain.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @Author hezhao
 * @Time 2018-07-22 22:36
 */
public interface CityService {

    List<City> findAll();

    List<City> findByCityName(String name);

    Page<City> query(String queryString, Pageable pageable);

    void save(City city);

}
