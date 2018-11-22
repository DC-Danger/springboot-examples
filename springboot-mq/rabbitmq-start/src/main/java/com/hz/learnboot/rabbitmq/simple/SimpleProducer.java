package com.hz.learnboot.rabbitmq.simple;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 简单模式 - 消息生产者
 *
 * 一个生产者对于一个消费者
 *
 * Created by hezhao on 2018-07-26 08:57
 */
public class SimpleProducer {

    private final static String QUEUE_NAME = "simple.message";

    public static void main(String[] args) throws IOException, TimeoutException {
        // 创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();

        // 设置RabbitMQ相关信息
        factory.setHost("localhost");
        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setPort(5672);
        // 虚拟主机，默认为 /
        // factory.setVirtualHost("test_vhosts");

        // 创建一个新的连接
        Connection connection = factory.newConnection();
        //创建一个通道
        Channel channel = connection.createChannel();
        // 声明一个队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        //发送消息到队列中
        String message = "Hello RabbitMQ";
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));
        System.out.println("SimpleProducer Send +'" + message + "'");

        // 关闭通道和连接
        channel.close();
        connection.close();
    }

    /*
    注1：queueDeclare() - 声明要关注的队列
        第一个参数表示队列名称、
        第二个参数为是否持久化（true表示是，队列将在服务器重启时生存）、
        第三个参数为是否是独占队列（创建者可以使用的私有队列，断开后自动删除）、
        第四个参数为当所有消费者客户端连接断开时是否自动删除队列、
        第五个参数为队列的其他参数
    注2：basicPublish() - 发送消息
        第一个参数为交换机名称、
        第二个参数为队列映射的路由key、
        第三个参数为消息的其他属性、
        第四个参数为发送信息的主体
    */

}
