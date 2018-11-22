package com.hz.learnboot.kafka.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

/**
 * 消息发送者
 *
 * @Author hezhao
 * @Time 2018-07-28 12:01
 */
@Component
public class Sender {

    private Logger logger = LoggerFactory.getLogger(Sender.class);

    @Autowired
    private KafkaTemplate kafkaTemplate;

    /**
     * 发送消息
     * @param topic 主题名称
     * @param message 消息
     */
    public void sendMessage(String topic, String message){
        ListenableFuture future = kafkaTemplate.send(topic, message);
        future.addCallback(
                o -> logger.info("消息发送成功：" + message),
                throwable -> logger.error("消息发送失败：" + message)
        );
    }

    /**
     * 发送消息
     * @param topic 主题名称
     * @param key 键
     * @param message 消息
     */
    public void sendMessage(String topic, String key, String message){
        ListenableFuture future = kafkaTemplate.send(topic, key, message);
        future.addCallback(
                o -> logger.info("消息发送成功：" + message),
                throwable -> logger.error("消息发送失败：" + message)
        );
    }

}
