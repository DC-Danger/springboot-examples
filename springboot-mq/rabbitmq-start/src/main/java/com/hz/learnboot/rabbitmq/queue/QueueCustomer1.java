package com.hz.learnboot.rabbitmq.queue;

import com.hz.learnboot.rabbitmq.util.ConnectionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 工作队列模式 - 消息消费者1
 *
 * Created by hezhao on 2018-07-26 08:57
 */
public class QueueCustomer1 {

    private final static String QUEUE_NAME = "queue.message";

    public static void main(String[] args) throws IOException, TimeoutException {
        // 1.获取连接
        Connection connection = ConnectionUtil.getConnection();
        // 2.创建通道
        Channel channel = connection.createChannel();
        // 3.申明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        // 4.监听消息
        channel.basicConsume(QUEUE_NAME, true, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
                                       byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println("工作队列模式 消费者2接收:" + message);
            }
        });
    }

}
