package com.hz.learnboot.task.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.Executors;

/**
 * 定时调度配置类
 *
 * 需要实现SchedulingConfigurer接口
 *
 * Created by hezhao on 2018-07-11 19:59
 */
@Configuration
public class SchedulerConfig implements SchedulingConfigurer {

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        // SpringBoot本身默认的执行方式是串行执行，也就是说无论有多少task，都是一个线程串行执行，如果需要并行需手动配置
        // 开启一个线程调度池, 定时调度将变成并行任务
        taskRegistrar.setScheduler(Executors.newScheduledThreadPool(4));
    }

}