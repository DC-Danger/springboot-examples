package com.hz.learnboot.quartz.job;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Spring提供了MethodInvokingJobDetailFactoryBean，
 * 允许直接将JavaBean的方法定义为任务，而不必继承QuartzJobBean
 *
 * @author hezhao
 *
 */
@Service
public class LoggerJob {

    @Value("${message}")
    private String message;

    public void log(){
        String now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(new Date());
        System.out.println(now+" - Log : " + message);
    }

}

