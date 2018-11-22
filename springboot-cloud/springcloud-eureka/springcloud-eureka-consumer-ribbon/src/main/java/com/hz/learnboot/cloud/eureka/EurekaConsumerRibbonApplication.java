package com.hz.learnboot.cloud.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Spring Boot 启动类
 *
 * Created by hezhao on 28/06/2018.
 */
@SpringBootApplication
@EnableEurekaClient // 通过@EnableEurekaClient向服务中心注册
public class EurekaConsumerRibbonApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaConsumerRibbonApplication.class, args);
	}
}
