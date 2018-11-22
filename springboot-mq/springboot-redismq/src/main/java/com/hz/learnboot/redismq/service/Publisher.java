package com.hz.learnboot.redismq.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * 消息生产者
 *
 * @Author hezhao
 * @Time 2018-07-24 23:04
 */
@Service
public class Publisher {

    @Autowired
    private StringRedisTemplate redisTemplate;

    public String sendMessage(String name) {
        try {
            redisTemplate.convertAndSend("TOPIC_USERNAME", name);
            return "消息发送成功";

        } catch (Exception e) {
            e.printStackTrace();
            return "消息发送失败";
        }
    }

}
