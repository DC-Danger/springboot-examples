package com.hz.learnboot.redis;

import com.hz.learnboot.redis.util.RedisStrOperate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisStrTests {

    @Autowired
    private RedisStrOperate redisStrOperate;

    @Test
    public void testStr() {
        redisStrOperate.setStr("name", "horace");
        System.out.println("==================================testStr==================================");
        System.out.println(redisStrOperate.getStr("name"));
        System.out.println("==================================testStr==================================");
    }

}
