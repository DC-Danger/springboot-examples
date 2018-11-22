package com.hz.learnboot.rabbitmq.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 消息生产者
 *
 * Created by hezhao on 2018-07-26 10:22
 */
@Service
public class Sender {

    private Logger logger = LoggerFactory.getLogger(Sender.class);

    @Autowired
    private AmqpTemplate rabbitTemplate;

    /**
     * 发送消息， 支持简单、工作队列模式
     * @param queueName 队列名称
     * @param message 消息
     */
    public void sendMessage(String queueName, Object message){
        logger.info("正在发送：" + message);
        rabbitTemplate.convertAndSend(queueName, message);
    }

    /**
     * 发送消息，支持发布/订阅、路由、主题模式
     * @param exchangeName 交换机名称
     * @param routingKey 路由
     * @param message 消息
     */
    public void sendMessage(String exchangeName, String routingKey, Object message){
        logger.info("正在发送：" + message);
        rabbitTemplate.convertAndSend(exchangeName, routingKey, message);
    }

}
