package com.hz.learnboot.async.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 线程池配置类
 *
 * 参考：https://blog.csdn.net/qq_39385706/article/details/79365849
 *
 * @Author hezhao
 * @Time 2018-07-26 0:20
 */
@Configuration
@EnableAsync  // 启用异步任务
public class ThreadConfig {

    // 执行需要依赖线程池，这里就来配置一个线程池
    @Bean
    public Executor getExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10); // 核心线程数，默认为1
        executor.setMaxPoolSize(300); // 最大线程数，默认为Integer.MAX_VALUE
        executor.setQueueCapacity(500); // 队列最大长度，一般需要设置值>=notifyScheduledMainExecutor.maxNum；默认为Integer.MAX_VALUE
        executor.setKeepAliveSeconds(60); // 线程池维护线程所允许的空闲时间，默认为60秒
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy()); // 拒绝策略
        executor.initialize();
        return executor;
    }

}
