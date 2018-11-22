package com.hz.learnboot.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RabbitMQ Topic 主题模式 配置
 *
 * 路由键必须是一串字符，用句号（.） 隔开，比如说 agreements.us，或者 agreements.eu.stockholm 等。
 * 路由模式必须包含一个 星号（*），主要用于匹配路由键指定位置的一个单词，比如说，一个路由模式是这样子：agreements..b.*，那么就只能匹配路由键是这样子的：第一个单词是 agreements，第四个单词是 b。 井号（#）就表示相当于一个或者多个单词，例如一个匹配模式是agreements.eu.berlin.#，那么，以agreements.eu.berlin开头的路由键都是可以的。
 *
 * Created by hezhao on 2018-07-26 10:11
 */
@Configuration
public class TopicMQConfig {

    // 交换机名称
    public static final String EXCHANGE_NAME = "topicExchange";

    // 主题队列1
    @Bean
    public Queue topicQueue() {
        // 是否持久化
        boolean durable = true;
        // 仅创建者可以使用的私有队列，断开后自动删除
        boolean exclusive = false;
        // 当所有消费客户端连接断开后，是否自动删除队列
        boolean autoDelete = false;
        return new Queue("topic.message", durable, exclusive, autoDelete);
    }

    @Bean
    public Queue topicQueues() {
        // 是否持久化
        boolean durable = true;
        // 仅创建者可以使用的私有队列，断开后自动删除
        boolean exclusive = false;
        // 当所有消费客户端连接断开后，是否自动删除队列
        boolean autoDelete = false;
        return new Queue("topic.messages", durable, exclusive, autoDelete);
    }

    // topic 交换机
    @Bean
    TopicExchange topicExchange() {
        // 是否持久化
        boolean durable = true;
        // 当所有消费客户端连接断开后，是否自动删除队列
        boolean autoDelete = false;
        return new TopicExchange(EXCHANGE_NAME, durable, autoDelete);
    }

    // topic：将路由键和某模式进行匹配。此时队列需要绑定要一个模式上。符号 “#” 匹配一个或多个词，符号“”匹配不多不少一个词。
    @Bean
    Binding bindingExchangeMessage(Queue topicQueue, TopicExchange topicExchange) {
        return BindingBuilder.bind(topicQueue).to(topicExchange).with("topic.message");
    }

    // 因此发送“topic.message”会匹配到topic.#和topic.message， 所以两个Receiver都可以收到消息，发送“topic.messages”只有topic.#可以匹配，所以只有Receiver2监听到消息
    @Bean
    Binding bindingExchangeMessages(Queue topicQueues, TopicExchange topicExchange) {
        return BindingBuilder.bind(topicQueues).to(topicExchange).with("topic.#");
    }

}
