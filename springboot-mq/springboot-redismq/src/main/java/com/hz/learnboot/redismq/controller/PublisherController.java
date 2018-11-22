package com.hz.learnboot.redismq.controller;

import com.hz.learnboot.redismq.service.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 控制器
 *
 * @Author hezhao
 * @Time 2018-07-24 10:54
 */
@RestController
@RequestMapping("/publisher")
public class PublisherController {

    @Autowired
    private Publisher publisherService;

    @RequestMapping("{name}")
    public String sendMessage(@PathVariable("name") String name) {
        return publisherService.sendMessage(name);
    }

}
