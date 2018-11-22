package com.hz.learnboot.start.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/** 第一个控制器
 *
 * @Author hezhao
 * @Time 2018-06-28 22:54
 */
@RestController
public class SpringBootHelloController {

    @RequestMapping(value = {"/", "/hello"},method = RequestMethod.GET)
    public String hello() {
        return "Welcome to Spring Boot !";
    }

}
