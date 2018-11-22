package com.hz.learnboot.task.controller;

import com.hz.learnboot.task.dao.CronRepository;
import com.hz.learnboot.task.task.DynamicScheduledConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用于动态修改corn值
 *
 * Created by hezhao on 2018-07-11 19:53
 */
@RestController
public class TestController {

    @Autowired
    private DynamicScheduledConfig dynamicScheduledConfig;

    @Autowired
    private CronRepository cronRepository;

    // 修改动态定时任务的cron值， 8秒一次
    @GetMapping("/updateTask")
    public Object updateTask() {
        dynamicScheduledConfig.setCron("0/8 * * * * ?");
        return "success";
    }

    // 修改数据库中保存的Cron表达式
    @GetMapping("/updateCronByKey")
    public Object updateCronByKey() {
        cronRepository.updateCronByKey("default", "0/8 * * * * ?");
        return "success";
    }

}
