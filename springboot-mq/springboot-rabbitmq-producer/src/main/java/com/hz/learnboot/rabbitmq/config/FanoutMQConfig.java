package com.hz.learnboot.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RabbitMQ Fanout 发布/订阅模式 配置
 *
 * Fanout 就是我们熟悉的广播模式或者订阅模式，给Fanout交换机发送消息，绑定了这个交换机的所有队列都收到这个消息。
 *
 * Created by hezhao on 2018-07-26 10:11
 */
@Configuration
public class FanoutMQConfig {

    // 交换机名称
    public static final String EXCHANGE_NAME = "fanoutExchange";

    // 发布/订阅队列
    @Bean
    public Queue fanoutQueue1() {
        return new Queue("fanout.message1");
    }
    @Bean
    public Queue fanoutQueue2() {
        return new Queue("fanout.message2");
    }

    // Fanout 交换机
    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange(EXCHANGE_NAME);
    }

    // fanout：不处理路由键。只需要简单的将队列绑定到交换机上。一个发送到交换机的消息都会被转发到与该交换机绑定的所有队列上。
    // 很像子网广播，每台子网内的主机都获得了一份复制的消息。fanout 类型交换机转发消息是最快的。
    @Bean
    Binding bindingFanoutExchange1(Queue fanoutQueue1, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanoutQueue1).to(fanoutExchange);
    }
    @Bean
    Binding bindingFanoutExchange2(Queue fanoutQueue2, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanoutQueue2).to(fanoutExchange);
    }

}
