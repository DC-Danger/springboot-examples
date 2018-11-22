package com.hz.learnboot.redis;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by hezhao on 2018-07-05 11:09
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class StringRedisTemplateTests {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void test1() {
        ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
        valueOperations.set("nickName", "小乌龟");
        System.out.println("==================================test1==================================");
        System.out.println(valueOperations.get("nickName"));
        Assert.assertEquals("小乌龟", valueOperations.get("nickName"));
        System.out.println("==================================test1==================================");
    }
}
