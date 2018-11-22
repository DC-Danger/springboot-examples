package com.hz.learnboot.zipkin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 对外暴露接口
 *
 * Created by hezhao on 2018-08-02 09:13
 */
@RestController
public class HiController {

    private static final Logger logger = LoggerFactory.getLogger(HiController.class);

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/hi")
    public String callHome(){
        logger.info("calling trace service-hi");
        return restTemplate.getForObject("http://localhost:8781/miya", String.class);
    }
    @RequestMapping("/info")
    public String info(){
        logger.info("calling trace service-hi");

        return "i'm service-hi";

    }

}
