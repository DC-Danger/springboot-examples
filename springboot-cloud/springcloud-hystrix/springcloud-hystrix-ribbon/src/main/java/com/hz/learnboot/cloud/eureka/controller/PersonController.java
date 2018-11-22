package com.hz.learnboot.cloud.eureka.controller;

import com.hz.learnboot.cloud.eureka.domain.Person;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 测试控制器
 *
 * 通过之前注入ioc容器的restTemplate来消费service-provider务的“/person/getPersonById”接口
 *
 * Created by hezhao on 2018-08-01 11:50
 */
@RestController
@RequestMapping("/person-ribbon")
public class PersonController {

    private Logger logger = LoggerFactory.getLogger(PersonController.class);

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/getPersonById/{id}")
    @HystrixCommand(fallbackMethod = "getPersonByIdError") // 对该方法创建了熔断器的功能，并指定了fallbackMethod熔断方法
    public Object getPersonById(@PathVariable("id") Long id) {
        // 在这里我们直接用的程序名替代了具体的url地址，在ribbon中它会根据服务名来选择具体的服务实例，根据服务实例在请求的时候会用具体的url替换掉服务名
        Person person = restTemplate.getForObject("http://SERVICE-PROVIDER/person/getPersonById/" + id, Person.class);
        return person;
    }

    // 熔断方法， 服务出错时将被调用
    // 方法返回类型必须与原方法一致
    public Object getPersonByIdError(Long id) {
        logger.error("熔断方法getPersonByIdError()被调用！ id:{}", id);
        return "系统异常，id=" + id;
    }

}
