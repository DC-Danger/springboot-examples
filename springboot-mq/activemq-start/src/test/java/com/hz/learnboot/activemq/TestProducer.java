package com.hz.learnboot.activemq;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 测试生产者
 *
 * Created by hezhao on 2018-07-24 15:02
 */
public class TestProducer {

    public static void main(String[] args) {
        Producer producer = new Producer();
        producer.init();
        TestProducer testMq = new TestProducer();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 固定数量的线程池
        int poolSize = 4;
        ExecutorService pool = Executors.newFixedThreadPool(poolSize);

        // 开启4个线程
        for (int i = 0; i < poolSize; i++) {
            pool.execute(new Thread(testMq.new ProducerMq(producer)));
        }

        // 关闭线程池
        pool.shutdown();
    }

    private class ProducerMq implements Runnable{
        Producer producer;
        String queueName = "Horace-MQ";

        public ProducerMq(Producer producer){
            this.producer = producer;
        }

        @Override
        public void run() {
            while(true){
                try {
                    producer.sendMessage(queueName);
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
