package com.hz.learnboot.rocketmq;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import org.apache.rocketmq.spring.starter.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.starter.core.RocketMQListener;
import org.apache.rocketmq.spring.starter.core.RocketMQPushConsumerLifecycleListener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Spring Boot 应用启动类
 *
 * Created by hezhao on 24/07/2018.
 */
@SpringBootApplication
public class Application {

	public static void main(String[] args){
		SpringApplication.run(Application.class, args);
	}

	@Slf4j
	@Service
	@RocketMQMessageListener(topic = "test-topic-1", consumerGroup = "my-consumer_test-topic-1")
	public class MyConsumer1 implements RocketMQListener<String>{
		public void onMessage(String message) {
			log.info("received message: {}", message);
		}
	}

	@Slf4j
	@Service
	@RocketMQMessageListener(topic = "test-topic-2", consumerGroup = "my-consumer_test-topic-2")
	public class MyConsumer2 implements RocketMQListener<OrderPaidEvent>{
		public void onMessage(OrderPaidEvent orderPaidEvent) {
			log.info("received orderPaidEvent: {}", orderPaidEvent);
		}
	}


	@Data
	@AllArgsConstructor
	public class OrderPaidEvent implements Serializable {
		private String orderId;
		private BigDecimal paidMoney;
	}
}
