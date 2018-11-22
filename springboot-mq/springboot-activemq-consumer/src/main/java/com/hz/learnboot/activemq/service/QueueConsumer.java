package com.hz.learnboot.activemq.service;

import com.hz.learnboot.activemq.domain.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;

/**
 * 消息消费者
 * Queue支持存在多个消费者，但是对一个消息而言，只会有一个消费者可以消费。
 * Queue保证每条数据都能被receiver接收。
 * 没有消费的消息会持久化，不会丢失
 *
 * Created by hezhao on 2018-07-25 09:08
 */
@Component
public class QueueConsumer {

    private Logger logger = LoggerFactory.getLogger(QueueConsumer.class);

    /**
     * 使用JmsListener配置消费者监听的队列
     * @param message 消息对象
     */
    @JmsListener(destination = "sample.queue", containerFactory="queueListenerFactory") // 增加对应处理的监听器工程
    public void receiveQueue1(ObjectMessage message) throws JMSException {
        // 接收对象使用ObjectMessage 接收消息
        Person person = (Person) message.getObject();
        logger.info(Thread.currentThread().getName() + " - QueueConsumer1 - 我是消费者1，我正在消费Msg:<" + person + ">");
    }

    /**
     * 使用JmsListener配置消费者监听的队列
     * @param message 消息对象
     */
    @JmsListener(destination = "sample.queue", containerFactory="queueListenerFactory") // 增加对应处理的监听器工程
    public void receiveQueue2(ObjectMessage message) throws JMSException {
        // 接收对象使用ObjectMessage 接收消息
        Person person = (Person) message.getObject();
        logger.info(Thread.currentThread().getName() + " - QueueConsumer2 - 我是消费者2，我正在消费Msg:<" + person + ">");
    }

}
