package com.hz.learnboot.activemq.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;

import javax.jms.ConnectionFactory;
import java.util.concurrent.Executors;

/**
 * 消费者工厂配置
 *
 * ActiveMQ 在注解实例化消费者时候只会生成一种bean，所以要同时支持queue和topic两种模式的话需要在此处配置。
 * 如果此处不做配置默认为queue模式。
 *
 * Created by hezhao on 2018-07-25 14:39
 */
@Configuration
@EnableJms
public class ActiveMQConfig {

    // topic模式的ListenerContainer
    @Bean("topicListenerFactory")
    public JmsListenerContainerFactory<?> topicListenerFactory(ConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        // 将pubSubDomain设置成true，表示该Listener负责处理Topic
        factory.setPubSubDomain(true);
        factory.setConnectionFactory(connectionFactory);
        // 开启线程池，并发消费
        factory.setTaskExecutor(Executors.newFixedThreadPool(4));
        return factory;
    }

    // queue模式的ListenerContainer
    @Bean("queueListenerFactory")
    public JmsListenerContainerFactory<?> queueListenerFactory(ConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        // 将pubSubDomain设置成false，该Listener主要负责处理Queue。也就是说，jms默认就是queue模式。
        factory.setPubSubDomain(false);
        factory.setConnectionFactory(connectionFactory);
        // 开启线程池，并发消费
        factory.setTaskExecutor(Executors.newFixedThreadPool(4));
        return factory;
    }

}
