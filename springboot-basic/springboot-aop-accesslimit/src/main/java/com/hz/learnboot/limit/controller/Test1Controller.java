package com.hz.learnboot.limit.controller;

import com.hz.learnboot.limit.annotaion.RequestLimit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/** 第一个控制器
 *
 * @Author hezhao
 * @Time 2018-06-28 22:54
 */
@RestController
public class Test1Controller {

    private Logger logger = LoggerFactory.getLogger(Test1Controller.class);

    @GetMapping("/test1")
    @RequestLimit(count = 10, time = 5000)
    public String test1(HttpServletRequest request) {
        logger.info("test1...");
        return "test1";
    }

}
