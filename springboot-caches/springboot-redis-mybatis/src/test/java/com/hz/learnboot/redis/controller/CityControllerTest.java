package com.hz.learnboot.redis.controller;

import com.hz.learnboot.redis.SpringBootRedisMybatisApplication;
import com.hz.learnboot.redis.domain.City;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by hezhao on 2018-07-05 16:11
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SpringBootRedisMybatisApplication.class})
public class CityControllerTest {

    @Autowired
    private CityController cityController;

    @Test
    public void findAllCity() {
        List<City> city = cityController.findAllCity();
        System.out.println(city);
    }

}