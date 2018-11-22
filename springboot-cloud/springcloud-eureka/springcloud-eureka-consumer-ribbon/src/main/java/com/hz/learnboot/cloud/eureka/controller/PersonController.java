package com.hz.learnboot.cloud.eureka.controller;

import com.hz.learnboot.cloud.eureka.domain.Person;
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

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/getPersonById/{id}")
    public Person getPersonById(@PathVariable("id") Long id) {
        // 在这里我们直接用的程序名替代了具体的url地址，在ribbon中它会根据服务名来选择具体的服务实例，根据服务实例在请求的时候会用具体的url替换掉服务名
        Person person = restTemplate.getForObject("http://SERVICE-PROVIDER/person/getPersonById/" + id, Person.class);
        return person;
    }

}
