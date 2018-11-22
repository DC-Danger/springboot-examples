package com.hz.learnboot.cache.service.jsr107;

import java.util.Date;
import javax.cache.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 使用的jsr-107标准注解，没有使用Spring的一些注解（如@Cachable等）
 *
 * Created by hezhao on 2018-07-06 16:56
 */
@Component
@CacheDefaults(cacheName = "peopleCache")
public class PersonService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonService.class);

    // 类似于@Cachable,如果有数据，则直接返回缓存数据；没有就保存到缓存
    @CacheResult
    public Person getPerson(int ssn) {
        LOGGER.info("ssn " + ssn + " not found in cache. TimeStamp: {}", new Date());

        switch (ssn) {
            case 123456789:
                return new Person(ssn, "Geoff", "Gibson");
            case 987654321:
                return new Person(ssn, "Cory", "Beck");
            default:
                return new Person(ssn,"John","Doe");
        }
    }

    // 修改缓存
    @CachePut
    public void savePerson(@CacheKey int ssn, @CacheValue Person person) { // 使用@CacheKey指定key, @CacheValue指定返回值
        LOGGER.info("修改缓存 - ssn - " + ssn + ", person is: " + person);

    }

    // 删除缓存
    @CacheRemove
    public void removePerson(int ssn) {
        System.out.println("删除缓存 - ssn - " + ssn);
    }

}
