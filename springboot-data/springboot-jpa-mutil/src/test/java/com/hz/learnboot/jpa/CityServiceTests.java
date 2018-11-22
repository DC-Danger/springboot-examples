package com.hz.learnboot.jpa;

import com.hz.learnboot.jpa.domain.secondary.City;
import com.hz.learnboot.jpa.service.CityService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Author hezhao
 * @Time 2018-07-01 22:59
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CityServiceTests {
    @Autowired
    private CityService cityService;

    @Test
    public void testQueryALl(){
        List<City> cityList = cityService.queryAll();
        System.out.println("==================================testQueryALl==================================");
        cityList.forEach(System.out::println);
        System.out.println("==================================testQueryALl==================================");
    }

    @Test
    public void testFindByCityName(){
        City city = cityService.findByCityName("深圳市");
        System.out.println("==================================testFindByName==================================");
        System.out.println(city);
        System.out.println("==================================testFindByName==================================");
    }
}
