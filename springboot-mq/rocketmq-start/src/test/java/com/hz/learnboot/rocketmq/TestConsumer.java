package com.hz.learnboot.rocketmq;

/**
 * 测试消费者
 *
 * Created by hezhao on 2018-07-24 15:02
 */
public class TestConsumer {

    private static String topic = "test";

    public static void main(String[] args) {
        Consumer consumer = new Consumer();
        consumer.getMessage(topic, "*");
    }

}
