package com.hz.learnboot.quartz;

import com.hz.learnboot.quartz.job.HelloJob;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * Created by hezhao on 2018-07-12 10:27
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class HelloScheduler {

    @Test
    public void test() throws SchedulerException, InterruptedException {
        // 创建一个jobDetail的实例，将该实例与HelloJob Class绑定
        JobDetail jobDetail = JobBuilder
                .newJob(HelloJob.class)
                .withIdentity("myJob")
                .usingJobData("message", "I'm Jack!")
                .build();

        // 创建一个Trigger触发器的实例，定义该job立即执行，并且每2秒执行一次，一直执行
        SimpleTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("myTrigger").startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2).repeatForever()).build();

        // 创建schedule实例
        StdSchedulerFactory factory = new StdSchedulerFactory();
        Scheduler scheduler = factory.getScheduler();

        // 注册任务和触发器
        scheduler.scheduleJob(jobDetail, trigger);
        // 获取给定时间的下一个完整分钟的时间，例如给定时间 08:13:54 则会反回 08:14:00
        Date runTime = DateBuilder.evenMinuteDate(new Date());
        System.out.println(jobDetail.getKey() + " 将会运行于: " + runTime);

        // 启动调度
        scheduler.start();

        System.out.println("------- 开始安排 -----------------");

        System.out.println("------- 等待65秒 -------------");
        Thread.sleep(65L * 1000L);

        // 关闭调度
        System.out.println("------- 关闭 ---------------------");
        scheduler.shutdown(true);
        System.out.println("------- 关闭完成 -----------------");
    }

}
