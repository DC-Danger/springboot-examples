package com.hz.learnboot.rabbitmq.queue;

import com.hz.learnboot.rabbitmq.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 工作队列模式 - 消息生产者
 *
 * 一个生产者对于多个消费者
 *
 * Created by hezhao on 2018-07-26 08:57
 */
public class QueueProducer {

    private final static String QUEUE_NAME = "queue.message";

    public static void main(String[] args) throws IOException, TimeoutException {
        //1.获取连接
        Connection connection = ConnectionUtil.getConnection();
        //2.创建通道
        Channel channel = connection.createChannel();
        //3.申明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        //4.发送消息
        for (int i = 0; i < 100; i++) {
            String message = "测试 工作队列模式 - " + i;
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));
            System.out.println("QueueProducer Send +'" + message + "'");
        }

        //5.释放连接
        channel.close();
        connection.close();
    }

}
