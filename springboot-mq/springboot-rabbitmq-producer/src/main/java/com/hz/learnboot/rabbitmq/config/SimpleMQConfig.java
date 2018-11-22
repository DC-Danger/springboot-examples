package com.hz.learnboot.rabbitmq.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RabbitMQ 工作队列 配置
 *
 * Created by hezhao on 2018-07-26 10:11
 */
@Configuration
public class SimpleMQConfig {

    public static final String QUEUE_NAME = "simple.message";

    // 工作队列
    @Bean
    public Queue simpleQueue() {
        return new Queue(QUEUE_NAME);
    }
}
