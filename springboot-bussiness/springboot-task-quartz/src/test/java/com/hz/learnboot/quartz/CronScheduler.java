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
public class CronScheduler {

    @Test
    public void test() throws SchedulerException, InterruptedException {
        // jobDetail
        JobDetail jobDetail = JobBuilder.newJob(HelloJob.class).withIdentity("cronJob").build();

        JobDataMap jobDataMap = jobDetail.getJobDataMap();
        jobDataMap.put("message", "使用CronTrigger");

        // cronTrigger
        // 五秒钟一次
        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity("cronTrigger")
                .withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ? ")).build();

        // 1.每日10点15分触发      0 15 10 ? * *
        // 2.每天下午的2点到2点59分（正点开始，隔5分触发）       0 0/5 14 * * ?
        // 3.从周一到周五每天的上午10点15触发      0 15 10 ? MON-FRI
        // 4.每月的第三周的星期五上午10点15触发     0 15 10 ? * 6#3
        // 5.2016到2017年每月最后一周的星期五的10点15分触发   0 15 10 ? * 6L 2016-2017

        // Scheduler实例
        StdSchedulerFactory stdSchedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = stdSchedulerFactory.getScheduler();

        // 注册任务和触发器
        scheduler.scheduleJob(jobDetail, cronTrigger);
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

