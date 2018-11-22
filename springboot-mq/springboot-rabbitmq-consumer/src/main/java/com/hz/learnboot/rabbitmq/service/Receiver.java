package com.hz.learnboot.rabbitmq.service;

import com.hz.learnboot.rabbitmq.domain.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * 消息消费者
 *
 * Created by hezhao on 2018-07-26 10:52
 */
@Service
public class Receiver {

    private Logger logger = LoggerFactory.getLogger(Receiver.class);

    // 通过 @RabbitListener 注解定义对队列的监听
    @RabbitListener(queues = "simple.message")
    public void receiveSimpleMessage1(String message) {
        logger.info(Thread.currentThread().getName() + " - Received Simple1 <" + message + ">");
    }
    @RabbitListener(queues = "simple.message")
    public void receiveSimpleMessage2(String message) {
        logger.info(Thread.currentThread().getName() + " - Received Simple2 <" + message + ">");
    }


    // 发布/订阅模式
    @RabbitListener(queues = "fanout.message1")
    public void receiveFanoutMessage1(String message) {
        logger.info(Thread.currentThread().getName() + " - Received Fanout1 <" + message + ">");
    }
    @RabbitListener(queues = "fanout.message2")
    public void receiveFanoutMessage2(String message) {
        logger.info(Thread.currentThread().getName() + " - Received Fanout2 <" + message + ">");
    }


    // 主题模式
    @RabbitListener(queues = "topic.message")
    public void receiveTopicMessage1(Person person) {
        logger.info(Thread.currentThread().getName() + " - Received topic.message <" + person + ">");
    }
    @RabbitListener(queues = "topic.messages")
    public void receiveTopicMessage2(Person person) {
        logger.info(Thread.currentThread().getName() + " - Received topic.messages <" + person + ">");
    }


    // 路由模式
    @RabbitListener(queues = "direct.messages")
    public void receiveDirectMessage(String message) {
        logger.info(Thread.currentThread().getName() + " - Received Direct <" + message + ">");
    }

}
