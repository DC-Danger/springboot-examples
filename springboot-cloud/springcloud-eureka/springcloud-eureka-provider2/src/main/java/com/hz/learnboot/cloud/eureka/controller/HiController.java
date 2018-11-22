package com.hz.learnboot.cloud.eureka.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hezhao on 2018-08-02 09:13
 */
@RestController
public class HiController {

    private static final Logger logger = LoggerFactory.getLogger(HiController.class);

    @Value("${server.port}")
    private String port;

    @RequestMapping("/hi/{name}")
    @HystrixCommand(fallbackMethod = "hiError") // 声明断路点HystrixCommand
    public String hi(@PathVariable("name") String name){
        return "hi, " + name + ", from port " + port;

    }

    // 熔断方法， 服务出错时将被调用
    // 方法返回类型必须与原方法一致
    public String hiError(String name) {
        logger.error("熔断方法hiError()被调用！ name:{}", name);
        return "系统异常， name=" + name;
    }

}
