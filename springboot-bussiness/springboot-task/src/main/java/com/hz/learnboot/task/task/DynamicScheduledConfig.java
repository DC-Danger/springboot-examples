package com.hz.learnboot.task.task;

import com.hz.learnboot.task.util.LoggerUtil;
import lombok.Setter;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.CronTask;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.config.TriggerTask;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 动态定时任务
 */
@Component
public class DynamicScheduledConfig implements SchedulingConfigurer {

    // 默认每2秒执行一次定时任务
    @Setter
    private String cron = "0/2 * * * * ?";

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        // 定时任务一
        taskRegistrar.addTriggerTask(() -> {
            // 此处执行定时任务的业务逻辑
            LoggerUtil.info(">>定时任务一<<");
        }, triggerContext -> {
            // 定时任务触发，可修改定时任务的执行周期
            CronTrigger trigger = new CronTrigger(cron);
            Date nextExecDate = trigger.nextExecutionTime(triggerContext);
            return nextExecDate;
        });

        // 定时任务二
        taskRegistrar.addTriggerTask(new TriggerTask(() -> {
            // 此处执行定时任务的业务逻辑
            LoggerUtil.info(">>定时任务二<<");
        },  triggerContext -> {
            // 定时任务触发，可修改定时任务的执行周期
            CronTrigger trigger = new CronTrigger(cron);
            Date nextExecDate = trigger.nextExecutionTime(triggerContext);
            return nextExecDate;
        }));

        // 定时任务三：此种不会因为cron的改变而改变任务执行时间
        taskRegistrar.addCronTask(() -> {
            // 此处执行定时任务的业务逻辑
            LoggerUtil.info("定时任务三");
        }, cron);

        // 定时任务四：此种不会因为cron的改变而改变任务执行时间
        taskRegistrar.addCronTask(new CronTask(() -> {
            // 此处执行定时任务的业务逻辑
            LoggerUtil.info("定时任务四");
        }, new CronTrigger(cron)));

    }

}
