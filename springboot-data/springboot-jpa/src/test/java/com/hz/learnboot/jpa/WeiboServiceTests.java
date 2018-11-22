package com.hz.learnboot.jpa;

import com.hz.learnboot.jpa.service.WeiboService;
import lombok.val;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author hezhao
 * @Time 2018-07-01 22:26
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class WeiboServiceTests {

    @Autowired
    private WeiboService weiboService;

    @Test
    public void testGetUserWeibo(){
        val list = weiboService.getUserWeibo("admin");
        System.out.println("==================================testGetUserWeibo==================================");
        list.forEach(System.out::println);
        System.out.println("==================================testGetUserWeibo==================================");
    }

    @Test
    public void testSearchWeibo(){
        val list = weiboService.searchWeibo("admin", "特金会", 1, 10);
        System.out.println("==================================testSearchWeibo==================================");
        list.forEach(System.out::println);
        System.out.println("==================================testSearchWeibo==================================");
    }

    @Test
    public void testSearchWeiboByCriteria(){
        val list = weiboService.searchWeiboByCriteria("admin", "特金会",null, null, 1, 10);
        System.out.println("==================================testSearchWeiboByCriteria==================================");
        list.forEach(System.out::println);
        System.out.println("==================================testSearchWeiboByCriteria==================================");
    }

    @Test
    public void testSearchWeiboByExampleMatcher(){
        val list = weiboService.searchWeiboByExampleMatcher("admin", "特金会");
        System.out.println("==================================testSearchWeiboByExampleMatcher==================================");
        list.forEach(System.out::println);
        System.out.println("==================================testSearchWeiboByExampleMatcher==================================");
    }

}
