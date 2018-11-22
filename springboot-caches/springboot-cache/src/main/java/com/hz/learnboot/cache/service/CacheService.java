package com.hz.learnboot.cache.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

/**
 * 服务类
 *
 * @Author hezhao
 * @Time 2018-07-05 22:56
 */
@Service
public class CacheService {

    // @Cacheable 在方法执行前 Spring 先查看缓存中是否有数据，如果有数据，则直接返回缓存数据；
    // 若没有数据，调用方法并将方法返回值放进缓存。
    // 有三个重要的值，
    //  value，返回的内容将存储在 value 定义的缓存的名字对象中。
    //  key，如果不指定将使用默认的 KeyGenerator 生成。
    //  condition，默认为空，表示将缓存所有的调用情形。其值是通过SpringEL表达式来指定的，当为true时表示进行缓存处理
    @Cacheable(value = "concurrenMapCache")
    public long getByCache() {
        try {
            Thread.sleep(3 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new Timestamp(System.currentTimeMillis()).getTime();
    }

    // @CachePut 与 @Cacheable 类似，但是它无论什么情况，都会将方法的返回值放到缓存中, 主要用于数据新增和修改方法。
    @CachePut(value = "concurrenMapCache")
    public long save() {
        long timestamp = new Timestamp(System.currentTimeMillis()).getTime();
        System.out.println("进行缓存：" + timestamp);
        return timestamp;
    }

    // @CacheEvict 将一条或多条数据从缓存中删除, 主要用于删除方法，用来从缓存中移除相应数据。
    @CacheEvict(value = "concurrenMapCache")
    public void delete() {
        System.out.println("删除缓存");
    }

}
