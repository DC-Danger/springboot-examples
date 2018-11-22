package com.hz.learnboot.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RabbitMQ Direct 路由模式 配置
 *
 * Created by hezhao on 2018-07-26 10:11
 */
@Configuration
public class DirectMQConfig {

    // 交换机名称
    public static final String EXCHANGE_NAME = "directExchange";

    // 路由队列
    @Bean
    public Queue directQueue() {
        return new Queue("direct.message");
    }

    // direct 交换机
    @Bean
    DirectExchange directExchange() {
        return new DirectExchange(EXCHANGE_NAME);
    }

    // direct：处理路由键。需要将一个队列绑定到交换机上，要求该消息与一个特定的路由键完全匹配。如果一个队列绑定到该交换机上要求路由键 “dog”，则只有被标记为 “dog” 的消息才被转发，不会转发 dog.puppy，也不会转发 dog.guard，只会转发dog。
    @Bean
    Binding bindingDirectExchange(Queue directQueue, DirectExchange directExchange) {
        return BindingBuilder.bind(directQueue).to(directExchange).with("demo");
    }

}
