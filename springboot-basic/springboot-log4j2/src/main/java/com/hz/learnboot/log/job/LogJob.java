package com.hz.learnboot.log.job;

import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 添加测试的 Job 类
 *
 * @Author hezhao
 * @Time 2018-07-24 2:18
 */
@Component
@Log4j2
public class LogJob {

    /**
     * 2秒钟执行1次
     */
    @Scheduled(fixedRate = 2 * 1000)
    public void logging(){
        Date now = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        log.info(simpleDateFormat.format(now));
        log.debug("-------DEBUG---------"); // 设置了level为info，所以debug级别的不会打印出来
        log.error(now.getTime());
    }

}
