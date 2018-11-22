package com.hz.learnboot.mybatis;

import com.github.pagehelper.PageHelper;
import com.hz.learnboot.mybatis.dao.cluster.CityMapper;
import com.hz.learnboot.mybatis.domain.cluster.City;
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
public class CityMapperTests {
    @Autowired
    private CityMapper cityMapper;

    @Test
    public void testQueryALl(){
        List<City> cityList = cityMapper.queryAll();
        System.out.println("==================================testQueryALl==================================");
        cityList.forEach(System.out::println);
        System.out.println("==================================testQueryALl==================================");
    }

    @Test
    public void testFindAllByPage() {
        List<City> pageInfo = getListByPage(2, 1);
        System.out.println("==================================testFindAllByPage==================================");
        pageInfo.forEach(e -> System.out.println(e));
        System.out.println("==================================testFindAllByPage==================================");
    }

    private List<City> getListByPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<City> cityList = cityMapper.queryAll();
        return cityList;
    }

    @Test
    public void testFindByName(){
        City city = cityMapper.findByName("深圳市");
        System.out.println("==================================testFindByName==================================");
        System.out.println(city);
        System.out.println("==================================testFindByName==================================");
    }
}
