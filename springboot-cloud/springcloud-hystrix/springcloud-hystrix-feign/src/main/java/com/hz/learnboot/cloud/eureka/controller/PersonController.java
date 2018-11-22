package com.hz.learnboot.cloud.eureka.controller;

import com.hz.learnboot.cloud.eureka.domain.Person;
import com.hz.learnboot.cloud.eureka.service.PersonFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试控制器
 *
 * Created by hezhao on 2018-08-01 11:50
 */
@RestController
@RequestMapping("/person-feign")
public class PersonController {

    // 编译器报错，无视。 因为这个Bean是在程序启动的时候注入的，编译器感知不到，所以报错。
    @Autowired
    private PersonFeignService personFeignService;

    @RequestMapping("/getPersonById/{id}")
    public Person getPersonById(@PathVariable("id") Long id) {
        return personFeignService.getPersonById(id);
    }

}
