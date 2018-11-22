package com.hz.learnboot.redismq;

import com.hz.learnboot.redismq.util.JedisFactory;
import redis.clients.jedis.Jedis;

/**
 * 消息生产者
 *
 * Created by hezhao on 2018-07-24 15:01
 */
public class Producer {

    public static final String MESSAGE_KEY = "message:queue";
    private Jedis jedis;
    private String producerName;
    private volatile int count;

    public Producer(String name) {
        this.producerName = name;
    }

    public void init() {
        jedis = JedisFactory.getJedis();
    }

    public void sendMessage() {
        String content = producerName + "正在生产东西！,count:" + count;
        Long size = jedis.lpush(MESSAGE_KEY, content);
        System.out.println(content);
        System.out.println(producerName + ": 当前未被处理消息条数为:" + size);
        count++;
    }

    public int getCount() {
        return count;
    }

}
