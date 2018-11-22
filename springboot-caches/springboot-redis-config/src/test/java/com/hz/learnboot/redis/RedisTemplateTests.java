package com.hz.learnboot.redis;

import com.hz.learnboot.redis.domain.UserInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

/**
 * Created by hezhao on 2018-07-05 11:09
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTemplateTests {
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void test1() throws Exception {
        UserInfo user = new UserInfo(99L, "李逵", "likui", "123456");
        ValueOperations<String, UserInfo> valueOperations = redisTemplate.opsForValue();
        valueOperations.set("com.likui", user);
        valueOperations.set("com.likui.f", user,1, TimeUnit.SECONDS);

        // Thread.sleep(1000);

        // redisTemplate.delete("com.likui.f");

        boolean exists = redisTemplate.hasKey("com.likui.f");
        if(exists){
            System.out.println("exists is true");
        }else{
            System.out.println("exists is false");
        }

        System.out.println("==================================test1==================================");
        System.out.println(valueOperations.get("com.likui"));
        Assert.assertEquals("李逵", valueOperations.get("com.likui.f").getName());
        System.out.println("==================================test1==================================");
    }
}
