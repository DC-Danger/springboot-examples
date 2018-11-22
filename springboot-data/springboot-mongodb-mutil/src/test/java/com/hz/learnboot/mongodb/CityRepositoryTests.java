package com.hz.learnboot.mongodb;

import com.hz.learnboot.mongodb.repository.primary.CityRepository;
import com.hz.learnboot.mongodb.domain.primary.City;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

/**
 * Created by hezhao on 2018-07-05 11:09
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CityRepositoryTests {
    @Autowired
    private CityRepository cityRepository;

    @Test
    public void testSave() {
        City city1 = City.builder()
                .id(440300L)
                .provinceId(440000L)
                .cityName("深圳市")
                .description("深圳，简称“深”，别称“鹏城”，是中国四大一线城市之一，广东省省辖市、计划单列市、副省级市、国家区域中心城市、超大城市，" +
                        "国务院定位的全国经济中心城市和国际化城市、国家创新型城市、国际科技产业创新中心、全球海洋中心城市、国际性综合交通枢纽，中国三大全国性金融中心之一。")
                .build();
        City city2 = City.builder()
                .id(441300L)
                .provinceId(440000L)
                .cityName("惠州市")
                .description("惠州，背靠罗浮山，南临大亚湾，境内东江蜿蜒100多公里，属珠江三角洲、粤港澳大湾区东岸。" +
                        "惠州毗邻深圳、香港，北连河源市，东接汕尾市，西邻东莞市和广州市，是珠江三角洲中心城市之一 [1]  。" +
                        "惠州辖惠城区、惠阳区、惠东县、博罗县、龙门县2区3县，并设有两个国家级开发区：大亚湾经济技术开发区、仲恺高新技术产业开发区。")
                .build();
        // 插入
        cityRepository.insert(city1);
        // 保存对象, 根据Id是否存在来决定是否是新增还是更新
        cityRepository.save(city2);
    }

    @Test
    public void testFindAll() {
        List<City> cityList = cityRepository.findAll();

        System.out.println("==================================testFindAll==================================");
        System.out.println(cityList);
        System.out.println("==================================testFindAll==================================");
    }

    @Test
    public void testFindById() {
        Optional<City> cityOptional = cityRepository.findById(440300L);

        System.out.println("==================================testFindById==================================");
        System.out.println(cityOptional.orElse(null));
        System.out.println("==================================testFindById==================================");
    }

    @Test
    public void testFindByCityName() {
        City city = cityRepository.findByCityName("深圳市");

        System.out.println("==================================testFindByCityName==================================");
        System.out.println(city);
        System.out.println("==================================testFindByCityName==================================");
    }

    @Test
    public void testSave2() {
        // 更新
        Optional<City> cityOptional = cityRepository.findById(441300L);
        if(cityOptional.isPresent()){
            City city = cityOptional.get();
            city.setDescription("惠州，深圳人的后花园。");
            cityRepository.save(city);
        } else {
            System.out.println("城市信息不存在。");
        }

    }

    @Test
    public void testDelete() {
        cityRepository.deleteById(441300L);
    }

}
