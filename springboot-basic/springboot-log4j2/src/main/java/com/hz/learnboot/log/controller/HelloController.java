package com.hz.learnboot.log.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/** 控制器
 *
 * @Author hezhao
 * @Time 2018-06-28 22:54
 */
@RestController
public class HelloController {

    private Logger logger = LoggerFactory.getLogger(HelloController.class);

    @RequestMapping(value = {"/", "/hello"},method = RequestMethod.GET)
    public String hello() {
        logger.info("into hello()");
        return "Welcome to Spring Boot !";
    }

}
