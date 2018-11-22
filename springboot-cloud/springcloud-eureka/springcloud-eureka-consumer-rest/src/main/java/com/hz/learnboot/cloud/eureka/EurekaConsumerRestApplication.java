package com.hz.learnboot.cloud.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * Spring Boot 启动类
 *
 * Created by hezhao on 28/06/2018.
 */
@SpringBootApplication
@EnableDiscoveryClient // 通过@EnableDiscoveryClient向服务中心注册
public class EurekaConsumerRestApplication {

	// 向程序的ioc注入一个bean: restTemplate
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(EurekaConsumerRestApplication.class, args);
	}
}
