package com.hz.learnboot.redismq.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;

/**
 * 消息消费者
 *
 * @Author hezhao
 * @Time 2018-07-24 23:17
 */
@Service

//// 1.继承MessageListener
//public class Receiver implements MessageListener {
//    private static Logger logger = LoggerFactory.getLogger(Receiver.class);
//
//    @Autowired
//    private StringRedisTemplate redisTemplate;
//
//    @Override
//    public void onMessage(Message message, byte[] pattern) {
//        RedisSerializer<String> valueSerializer = redisTemplate.getStringSerializer();
//        String deserialize = valueSerializer.deserialize(message.getBody());
//        logger.info("收到的mq消息" + deserialize);
//    }
//}

// 2.不继承
public class Receiver {
    private static Logger logger = LoggerFactory.getLogger(Receiver.class);

    public void receiveMessage(String message) {
        logger.info("收到的mq消息" + message);
    }
}