package com.hz.learnboot.task.task;

import com.hz.learnboot.task.dao.CronRepository;
import com.hz.learnboot.task.domain.Cron;
import com.hz.learnboot.task.util.LoggerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 动态定时任务（配合数据库动态执行）
 *
 * Created by hezhao on 2018-07-12 09:07
 */
@Component
public class DynamicScheduledByDBConfig implements SchedulingConfigurer {

    @Autowired
    private CronRepository cronRepository;

    /**
     * 执行定时任务
     */
    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.addTriggerTask(
                // 1.添加任务内容(Runnable)
                () -> {
                    LoggerUtil.info("执行定时任务（配合数据库动态执行）");
                },
                // 2.设置执行周期(Trigger)
                triggerContext -> {
                    // 2.1 从数据库获取执行周期
                    Cron cronDefault = cronRepository.findByKey("default");
                    // 2.2 合法性校验.
                    if (cronDefault == null || StringUtils.isEmpty(cronDefault.getCron())) {
                        // Omitted Code ..
                        LoggerUtil.info("default Cron 表达式为空！");
                    }
                    // 2.3 返回执行周期(Date)
                    return new CronTrigger(cronDefault.getCron()).nextExecutionTime(triggerContext);
                }
        );
    }
}
