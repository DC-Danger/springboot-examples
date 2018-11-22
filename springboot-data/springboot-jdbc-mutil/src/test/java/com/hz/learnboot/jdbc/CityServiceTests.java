package com.hz.learnboot.jdbc;

import com.hz.learnboot.jdbc.domain.City;
import com.hz.learnboot.jdbc.domain.UserInfo;
import com.hz.learnboot.jdbc.service.CityService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Author hezhao
 * @Time 2018-07-01 14:51
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
    public void testInsert() {
        cityService.insert(City.builder().id(361100L).provinceId(360000L).cityName("上饶1234567890123456789").description("无").build());
        System.out.println("==================================testInsert==================================");
        System.out.println("insert count is " + 2);
    }
}
