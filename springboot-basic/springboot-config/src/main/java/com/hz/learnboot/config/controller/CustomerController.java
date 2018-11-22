package com.hz.learnboot.config.controller;

import com.hz.learnboot.config.domain.CustomerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author hezhao
 * @Time 2018-06-28 23:07
 */
@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    private CustomerConfig customerConfig;

    @GetMapping
    public Object index() {
        Map<String, Object> resultMap = new HashMap<>();

        resultMap.put("info", customerConfig.getInfo());
        resultMap.put("blogs", customerConfig.getBlogs());

        return resultMap;
    }

}
