package com.hz.learnboot.task.task;

import com.hz.learnboot.task.util.LoggerUtil;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Spring动态周期定时任务<br>
 * 在不停应用的情况下更改任务执行周期
 *
 * Created by hezhao on 2018-07-12 09:24
 */
@Lazy(false)
@Component
public class SpringDynamicCronTask implements SchedulingConfigurer {
    private static String cron;

    public SpringDynamicCronTask() {
        cron = "0/5 * * * * ?";

        // 开启新线程模拟外部更改了任务执行周期
        new Thread(() -> {
            try {
                Thread.sleep(15 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // 执行周期由5秒变成了10秒
            cron = "0/10 * * * * ?";
            LoggerUtil.error("cron change to: " + cron);
        }).start();
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.addTriggerTask(() -> {
            // 任务逻辑
            LoggerUtil.error("SpringDynamicCronTask is running...");
        }, triggerContext -> {
            // 任务触发，可修改任务的执行周期
            CronTrigger trigger = new CronTrigger(cron);
            Date nextExec = trigger.nextExecutionTime(triggerContext);
            return nextExec;
        });
    }
}
