package com.hz.learnboot.rabbitmq.fanout;

import com.hz.learnboot.rabbitmq.util.ConnectionUtil;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 发布/订阅模式 - 消息生产者
 *
 * 不处理路由键。你只需要简单的将队列绑定到交换机上。一个发送到交换机的消息都会被转发到与该交换机绑定的所有队列上。
 * 很像子网广播，每台子网内的主机都获得了一份复制的消息。Fanout交换机转发消息是最快的。
 *
 * Created by hezhao on 2018-07-26 08:57
 */
public class FanoutProducer {

    private final static String EXCHANGE_NAME = "fanoutExchange";

    public static void main(String[] args) throws IOException, TimeoutException {
        //1.获取连接
        Connection connection = ConnectionUtil.getConnection();
        //2.创建通道
        Channel channel = connection.createChannel();
        //3.申明交换机
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);
        //4.发送消息
        for (int i = 0; i < 100; i++) {
            String message = "测试 发布/订阅模式 - " + i;
            System.out.println("FanoutProducer Send +'" + message + "'");
            channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes("UTF-8"));
        }

        //5.释放连接
        channel.close();
        connection.close();
    }

}
