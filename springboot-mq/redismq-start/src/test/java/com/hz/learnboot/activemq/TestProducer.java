package com.hz.learnboot.activemq;

import com.hz.learnboot.redismq.Producer;

import java.util.concurrent.TimeUnit;

/**
 * 测试生产者
 *
 * 由于Redis的列表是使用双向链表实现的，保存了头尾节点，所以在列表头尾两边插取元素都是非常快的。
 * 所以可以直接使用Redis的List实现消息队列，只需简单的两个指令lpush和rpop或者rpush和lpop。
 *
 * Created by hezhao on 2018-07-24 15:02
 */
public class TestProducer {

    public static void main(String[] args) {
        Producer producer = new Producer("Horace-MQ-Producer");
        producer.init();
        TestProducer testMq = new TestProducer();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(testMq.new ProducerMq(producer)).start();

        try {
            for(; ;) {
                System.out.println("main : 已存储消息条数:" + producer.getCount());
                TimeUnit.SECONDS.sleep(10);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private class ProducerMq implements Runnable{
        Producer producer;

        public ProducerMq(Producer producer){
            this.producer = producer;
        }

        @Override
        public void run() {
            while(true){
                try {
                    producer.sendMessage();
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
