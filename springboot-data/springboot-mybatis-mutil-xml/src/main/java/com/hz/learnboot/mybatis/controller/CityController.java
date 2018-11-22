package com.hz.learnboot.mybatis.controller;

import com.hz.learnboot.mybatis.dao.cluster.CityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hezhao on 2018-07-13 12:34
 */
@RestController
@RequestMapping("city")
public class CityController {

    @Autowired
    private CityMapper cityMapper;

    @GetMapping
    public Object list() {
        return cityMapper.queryAll();
    }

}
