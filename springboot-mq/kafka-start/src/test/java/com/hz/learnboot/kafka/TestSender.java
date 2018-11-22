package com.hz.learnboot.kafka;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 测试生产者
 *
 * Created by hezhao on 2018-07-24 15:02
 */
public class TestSender {

    // 用于计数
    private static AtomicInteger count = new AtomicInteger(0);
    private static String topic = "test";

    public static void main(String[] args) {
        Sender producer = new Sender(topic);
        TestSender testMq = new TestSender();

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
        Sender producer;

        public ProducerMq(Sender producer){
            this.producer = producer;
        }

        @Override
        public void run() {
            while(true){
                try {
                    // 创建一条文本消息
                    int num = count.getAndIncrement();
                    String message = Thread.currentThread().getName() + " - Producer:我是生产者，我现在正在生产东西！,count:" + num;
                    // 发送消息
                    producer.sendMessage(message);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
