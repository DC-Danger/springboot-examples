package com.hz.learnboot.rabbitmq.direct;

import com.hz.learnboot.rabbitmq.util.ConnectionUtil;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 路由模式 - 消息生产者
 *
 * 处理路由键。需要将一个队列绑定到交换机上，要求该消息与一个特定的路由键完全匹配。这是一个完整的匹配。
 * 如果一个队列绑定到该交换机上要求路由键 “test”，则只有被标记为“test”的消息才被转发，不会转发test.aaa，也不会转发dog.123，只会转发test。
 *
 * Created by hezhao on 2018-07-26 08:57
 */
public class DirectProducer {

    private final static String EXCHANGE_NAME = "directExchange";

    public static void main(String[] args) throws IOException, TimeoutException {
        //1.获取连接
        Connection connection = ConnectionUtil.getConnection();
        //2.创建通道
        Channel channel = connection.createChannel();
        //3.申明交换机
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
        //4.发送消息
        for (int i = 0; i < 100; i++) {
            String message = "测试 路由模式 - " + i;
            System.out.println("DirectProducer Send +'" + message + "'");
            // 此处demo为路由键
            channel.basicPublish(EXCHANGE_NAME, "demo", null, message.getBytes("UTF-8"));
        }

        //5.释放连接
        channel.close();
        connection.close();
    }

}
