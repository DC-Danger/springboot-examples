package com.hz.learnboot.activemq.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.Queue;
import javax.jms.Topic;

/**
 * 声明队列
 *
 * Created by hezhao on 2018-07-26 10:11
 */
@Configuration
public class ActiveMQConfig {

    // Queue队列
    @Bean("sampleQueue")
    public Queue sampleQueue() {
        return new ActiveMQQueue("sample.queue");
    }

    // Topic队列
    @Bean("sampleTopic")
    public Topic sampleTopic() {
        return new ActiveMQTopic("sample.topic");
    }

}
