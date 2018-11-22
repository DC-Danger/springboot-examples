package com.hz.learnboot.cloud.eureka.service;

import com.hz.learnboot.cloud.eureka.domain.Person;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Feign接口
 *
 * Created by hezhao on 2018-08-01 16:53
 */
@FeignClient(value = "SERVICE-PROVIDER", fallback = SchedualServicePersonHystric.class) // 加上fallback的指定类
public interface PersonFeignService {

    @RequestMapping("/person/getPersonById/{id}")
    Person getPersonById(@PathVariable("id") Long id);

}
