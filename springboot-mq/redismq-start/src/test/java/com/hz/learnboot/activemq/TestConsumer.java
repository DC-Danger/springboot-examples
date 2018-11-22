package com.hz.learnboot.activemq;

import com.hz.learnboot.redismq.Consumer;

/**
 * 测试消费者
 *
 * Created by hezhao on 2018-07-24 15:02
 */
public class TestConsumer {

    public static void main(String[] args) {
        Consumer customer = new Consumer("Horace-MQ-Consumer");
        customer.init();
        TestConsumer testConsumer = new TestConsumer();

        new Thread(testConsumer.new ConsumerMq(customer)).start();
    }

    private class ConsumerMq implements Runnable{
        Consumer consumer;

        public ConsumerMq(Consumer consumer){
            this.consumer = consumer;
        }

        @Override
        public void run() {
            while(true){
                try {
                    consumer.getMessage();
                    Thread.sleep(400);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
