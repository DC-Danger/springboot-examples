package com.hz.learnboot.rabbitmq.simple;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 简单模式 - 消息消费者
 *
 * Created by hezhao on 2018-07-26 08:57
 */
public class SimpleCustomer {

    private final static String QUEUE_NAME = "simple.message";

    public static void main(String[] args) throws IOException, TimeoutException {
        // 创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();

        // 设置RabbitMQ地址
        factory.setHost("localhost");
        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setPort(5672);
        // 虚拟主机，默认为 /
        // factory.setVirtualHost("test_vhosts");

        // 创建一个新的连接
        Connection connection = factory.newConnection();
        // 创建一个通道
        Channel channel = connection.createChannel();
        // 声明要关注的队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println("SimpleCustomer Waiting Received messages");

        // DefaultConsumer类实现了Consumer接口，通过传入一个频道，
        // 告诉服务器我们需要那个频道的消息，如果频道中有消息，就会执行回调函数handleDelivery
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println("SimpleCustomer Received '" + message + "'");
            }
        };

        // autoAck是否自动回复，如果为true的话，每次生产者只要发送信息就会从内存中删除，关闭的话则为手动回复，每当消费者收到并处理信息然后在通知生成者。最后从队列中删除这条信息。
        // 自动回复队列应答 -- RabbitMQ中的消息确认机制
        channel.basicConsume(QUEUE_NAME, true, consumer);
    }

}
