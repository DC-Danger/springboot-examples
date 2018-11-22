package com.hz.learnboot.dubbo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

import java.util.concurrent.CountDownLatch;

/**
 * 启动服务
 *
 * Created by hezhao on 2018-07-13 17:29
 */
@SpringBootApplication
@ImportResource({"classpath:dubbo-provider.xml"})
public class Application {

    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    public static void main(String[] args) throws InterruptedException {
        // Spring Boot 方式启动
        // registerShutdownHook()以及countDownLatch 可以让非Web项目一直运行下去
        SpringApplication.run(Application.class, args).registerShutdownHook();
        countDownLatch.await();
    }
}
