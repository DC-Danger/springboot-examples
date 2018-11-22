package com.hz.learnboot.memcached;

import com.danga.MemCached.MemCachedClient;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * Created by hezhao on 2018-07-05 11:09
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootMemcachedApplicationTests {

    @Autowired
    private MemCachedClient memCachedClient;

    @Before
    public void setEncoding() {
        // 设置默认编码
        memCachedClient.setDefaultEncoding("UTF-8");
    }

    @Test
    public void contextLoads() throws InterruptedException {
        // 放入缓存
        boolean flag = memCachedClient.set("a", 1);

        // 取出缓存
        Object a = memCachedClient.get("a");
        System.out.println(a);

        // 3s后过期
        // 使用danga memcached设置失效时间有两种方式：
        memCachedClient.set("b", "你好，世界！", new Date(3000));
        // memCachedClient.set("xx", "xx", new Date(System.currentTimeMillis() + 3 * 1000));
        Object b = memCachedClient.get("b");
        System.out.println(b);

        Thread.sleep(3000);
        b = memCachedClient.get("b");
        System.out.println(b);
    }

}