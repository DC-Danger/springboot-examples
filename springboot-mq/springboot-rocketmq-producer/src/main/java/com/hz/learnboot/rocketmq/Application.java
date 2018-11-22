package com.hz.learnboot.rocketmq;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.rocketmq.spring.starter.core.RocketMQTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Spring Boot 应用启动类
 *
 * Created by hezhao on 24/07/2018.
 */
@SpringBootApplication
public class Application implements CommandLineRunner {
	@Resource
	private RocketMQTemplate rocketMQTemplate;

	public static void main(String[] args){
		SpringApplication.run(Application.class, args);
	}

	public void run(String... args) throws Exception {
		rocketMQTemplate.convertAndSend("test-topic-1", "Hello, World!");
		rocketMQTemplate.send("test-topic-1", MessageBuilder.withPayload("Hello, World! I'm from spring message").build());
		rocketMQTemplate.convertAndSend("test-topic-2", new OrderPaidEvent("T_001", new BigDecimal("88.00")));

//        rocketMQTemplate.destroy(); // notes:  once rocketMQTemplate be destroyed, you can not send any message again with this rocketMQTemplate
	}

	@Data
	@AllArgsConstructor
	public class OrderPaidEvent implements Serializable {
		private String orderId;
		private BigDecimal paidMoney;
	}
}