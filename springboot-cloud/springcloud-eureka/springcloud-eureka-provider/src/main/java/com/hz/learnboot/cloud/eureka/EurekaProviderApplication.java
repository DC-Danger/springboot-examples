package com.hz.learnboot.cloud.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * Spring Boot 启动类
 *
 * Created by hezhao on 28/06/2018.
 */
@SpringBootApplication
@EnableEurekaClient // 表明自己是一个EurekaClient
@EnableHystrix // 开启断路器
@EnableHystrixDashboard // 开启断路器监控
@EnableCircuitBreaker
public class EurekaProviderApplication {

	/**
	 * 访问地址
	 * http://localhost:8762/actuator/hystrix.stream
	 * http://localhost:8762/hystrix
	 * @param args
	 */

	public static void main(String[] args) {
		SpringApplication.run(EurekaProviderApplication.class, args);
	}
}
