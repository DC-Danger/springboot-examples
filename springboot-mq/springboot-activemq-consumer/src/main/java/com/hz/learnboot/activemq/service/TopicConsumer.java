package com.hz.learnboot.activemq.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

/**
 * 消息消费者
 * Topic支持存在多个消费者，发布到topic的消息会被所有订阅者消费。消息可以重复消费
 * 并不保证publisher发布的每条数据，Subscriber都能接受到。
 * 只有正在监听该topic地址的消费方才能收到消息，其他的消费方就会丢失了。
 *
 * Created by hezhao on 2018-07-25 09:08
 */
@Component
public class TopicConsumer {

    private Logger logger = LoggerFactory.getLogger(TopicConsumer.class);

    /**
     * 使用JmsListener配置消费者监听的队列
     * @param text 消息文本
     */
    @JmsListener(destination = "sample.topic", containerFactory="topicListenerFactory") // 增加对应处理的监听器工程
    public void receiveQueue1(String text) {
        logger.info(Thread.currentThread().getName() + " - TopicConsumer1 - 我是消费者1，我正在消费Msg:<" + text + ">");
    }

    /**
     * 使用JmsListener配置消费者监听的队列
     * 实现双向队列
     *
     * @param text 消息文本
     */
    @JmsListener(destination = "sample.topic", containerFactory="topicListenerFactory") // 增加对应处理的监听器工程
    @SendTo("out.queue") // SendTo 该注解的意思是将return回的值，再发送的"out.queue"队列中
    public void receiveQueue2(String text) {
        logger.info(Thread.currentThread().getName() + " - TopicConsumer2 - 我是消费者2，我正在消费Msg:<" + text + ">");
    }

}
