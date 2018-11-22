package com.hz.learnboot.jdbc.service.impl;

import com.hz.learnboot.jdbc.dao.CityDao;
import com.hz.learnboot.jdbc.domain.City;
import com.hz.learnboot.jdbc.service.CityService;
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
    private CityDao cityDao;

    @Override
    public List<City> queryAll() {
        return cityDao.queryAll();
    }

    // 使用value具体指定使用哪个事务管理器
    @Override
    @Transactional(value = "secondaryTransactionManager", readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = {Exception.class})
    public int insert(City city) {
        int count = cityDao.insert(city);
        if(city.getCityName().length() > 20){
            // cityName 字段长度必须小于等于 20，否则回滚事务
            throw new RuntimeException("city cityName is too long. ");
        }
        return count;
    }
}
