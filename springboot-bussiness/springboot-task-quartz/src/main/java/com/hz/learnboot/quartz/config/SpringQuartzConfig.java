package com.hz.learnboot.quartz.config;

import com.hz.learnboot.quartz.job.LoggerJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * Spring集成Quartz配置类
 *
 * Created by hezhao on 2018-07-12 11:07
 */
@Configuration
public class SpringQuartzConfig {

    // 将JavaBean的方法定义为任务
    @Bean("jobDetailLoggerManager")
    public MethodInvokingJobDetailFactoryBean methodInvokingJobDetailFactoryBean(@Autowired LoggerJob loggerManager) {
        MethodInvokingJobDetailFactoryBean methodInvokingJobDetailFactoryBean = new MethodInvokingJobDetailFactoryBean();
        // 配置要调用的Bean实例
        methodInvokingJobDetailFactoryBean.setTargetObject(loggerManager);
        // 配置要调用的方法
        methodInvokingJobDetailFactoryBean.setTargetMethod("log");
        return methodInvokingJobDetailFactoryBean;
    }

    // Spring还提供了CronTriggerBean来支持精确的时间规则定义
    @Bean("cronTriggerFactoryBean")
    public CronTriggerFactoryBean cronTriggerFactoryBean(@Qualifier("jobDetailLoggerManager") MethodInvokingJobDetailFactoryBean mijobDetail) {
        CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
        cronTriggerFactoryBean.setJobDetail(mijobDetail.getObject());
        cronTriggerFactoryBean.setCronExpression("1/2 * * * * ?");
        return cronTriggerFactoryBean;
    }

    // 配置SchedulerFactoryBean注册任务和触发器
    @Bean("schedulerFactoryBean")
    public SchedulerFactoryBean schedulerFactoryBean(@Qualifier("cronTriggerFactoryBean") CronTriggerFactoryBean cronTriggerFactoryBean) {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        schedulerFactoryBean.setTriggers(cronTriggerFactoryBean.getObject());
        return schedulerFactoryBean;
    }
}
