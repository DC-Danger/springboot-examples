package com.hz.learnboot.activemq.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Destination;

/**
 * 消息生产者
 *
 * Created by hezhao on 2018-07-25 09:06
 */
@Component
public class Producer {

    private Logger logger = LoggerFactory.getLogger(Producer.class);

    // 也可以注入JmsTemplate，JmsMessagingTemplate对JmsTemplate进行了封装
    @Autowired
    private JmsMessagingTemplate jmsTemplate;

    /**
     * 发送消息
     * @param destination 发送到的队列
     * @param message 待发送的消息
     */
    public void sendMessage(Destination destination, final Object message){
        logger.info("正在发送：" + message);
        jmsTemplate.convertAndSend(destination, message);
    }

    /**
     * 对Producer进行改造，使其既能生产报文，又能消费队列中的报文
     * @param text
     */
    @JmsListener(destination="out.queue")
    public void consumerMessage(String text){
        logger.info(Thread.currentThread().getName() + " - 从out.queue队列收到的回复报文为:" + text);
    }

}
