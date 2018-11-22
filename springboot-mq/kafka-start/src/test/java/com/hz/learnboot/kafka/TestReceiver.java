package com.hz.learnboot.kafka;

/**
 * 测试消费者
 *
 * Created by hezhao on 2018-07-24 15:02
 */
public class TestReceiver {

    private static String topic = "test";

    public static void main(String[] args) {
        Receiver consumer = new Receiver(topic);
        consumer.getMessage();
    }

}
