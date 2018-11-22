package com.hz.learnboot.activemq;

/**
 * 测试消费者
 *
 * Created by hezhao on 2018-07-24 15:02
 */
public class TestConsumer {

    public static void main(String[] args) {
        Consumer consumer = new Consumer();
        consumer.init();
        String queueName = "Horace-MQ";
        consumer.getMessage(queueName);
    }

}
