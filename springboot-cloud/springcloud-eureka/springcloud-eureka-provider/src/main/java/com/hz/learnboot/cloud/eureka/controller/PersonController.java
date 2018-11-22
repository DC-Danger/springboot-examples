package com.hz.learnboot.cloud.eureka.controller;

import com.hz.learnboot.cloud.eureka.domain.Person;
import com.hz.learnboot.cloud.eureka.service.PersonService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 注意：该 controller 是给服务消费者(springcloud-eureka-consumer)使用的内部服务，不是给浏览器端调用的。
 *
 * Created by hezhao on 2018-08-01 11:50
 */
@RestController
@RequestMapping("/person")
public class PersonController {

    private Logger logger = LoggerFactory.getLogger(PersonController.class);

    @Autowired
    private PersonService demoService;

    @RequestMapping("/getPersonById/{id}")
    @HystrixCommand(fallbackMethod = "getPersonByIdError") // 声明断路点HystrixCommand
    public Person getPersonById(@PathVariable("id") Long id) {
        return demoService.getPersonById(id);
    }

    // 熔断方法， 服务出错时将被调用
    // 方法返回类型必须与原方法一致
    public Person getPersonByIdError(Long id) {
        logger.error("熔断方法getPersonByIdError()被调用！ id:{}", id);
        return null;
    }

}
