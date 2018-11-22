package com.hz.learnboot.async.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

/**
 * 异步任务
 *
 * @Author hezhao
 * @Time 2018-07-26 0:22
 */
@Service
public class AsyncTaskService {

    private Logger logger = LoggerFactory.getLogger(AsyncTaskService.class);

    @Async    // 这里进行标注为异步任务，在执行此方法的时候，会单独开启线程来执行
    public void f1() {
        for (int i = 0; i < 100; i++) {
            logger.info("f1 : " + Thread.currentThread().getName() + "   " + UUID.randomUUID().toString());
            try {
                // 模拟耗时操作
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Async
    public void f2() {
        for (int i = 0; i < 100; i++) {
            logger.info("f2 : " + Thread.currentThread().getName() + "   " + UUID.randomUUID().toString());
            try {
                // 模拟耗时操作
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
