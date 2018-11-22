package com.hz.learnboot.rabbitmq.topic;

import com.hz.learnboot.rabbitmq.util.ConnectionUtil;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 主题模式 - 消息生产者
 *
 * 将路由键和某模式进行匹配。此时队列需要绑定要一个模式上。符号“#”匹配一个或多个词，符号“*”匹配不多不少一个词。
 * 因此“audit.#”能够匹配到“audit.irs.corporate”，但是“audit.*” 只会匹配到“audit.irs”。
 *
 * Created by hezhao on 2018-07-26 08:57
 */
public class TopicProducer {

    private final static String EXCHANGE_NAME = "topicExchange";

    public static void main(String[] args) throws IOException, TimeoutException {
        //1.获取连接
        Connection connection = ConnectionUtil.getConnection();
        //2.创建通道
        Channel channel = connection.createChannel();
        //3.申明交换机
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);
        //4.发送消息
        for (int i = 0; i < 100; i++) {
            String message = "测试 主题模式 - " + i;
            System.out.println("TopicProducer Send +'" + message + "'");
            // 匹配到key.1.2
            channel.basicPublish(EXCHANGE_NAME, "key.1.2", null, message.getBytes("UTF-8"));
        }

        //5.释放连接
        channel.close();
        connection.close();
    }

}
