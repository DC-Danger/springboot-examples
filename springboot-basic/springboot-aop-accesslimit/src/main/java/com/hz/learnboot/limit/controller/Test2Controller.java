package com.hz.learnboot.limit.controller;

import com.hz.learnboot.limit.annotaion.RequestLimit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/** 第一个控制器
 *
 * @Author hezhao
 * @Time 2018-06-28 22:54
 */
@Controller
public class Test2Controller {

    private Logger logger = LoggerFactory.getLogger(Test2Controller.class);

    @GetMapping("/test2")
    @ResponseBody
    @RequestLimit(count = 5, time = 10000)
    public String test2(HttpServletRequest request) {
        logger.info("test2...");
        return "test2";
    }

}
