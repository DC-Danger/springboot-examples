package com.hz.learnboot.activemq.subscribe;

import com.hz.learnboot.redismq.util.JedisFactory;
import redis.clients.jedis.Jedis;

/**
 * 发布/订阅模式
 * 消息发布方
 *
 * Created by hezhao on 2018-07-24 18:29
 */
public class Publisher {

    public static final String CHANNEL_KEY = "channel:message";
    private Jedis jedis;

    public Publisher() {
        jedis = JedisFactory.getJedis();
    }

    public void publishMessage(String message) {
        if(message == null || message.length() == 0) {
            return;
        }
        jedis.publish(CHANNEL_KEY, message);
    }

    public static void main(String[] args) {
        Publisher publisher = new Publisher();
        publisher.publishMessage("Hello RedisMQ!");
        publisher.publishMessage("My Name is Horace.");
        publisher.publishMessage("exit");
    }

}
