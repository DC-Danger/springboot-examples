package com.hz.learnboot.cloud.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

/**
 * Spring Boot 启动类
 *
 * Created by hezhao on 28/06/2018.
 */
@SpringBootApplication
@EnableEurekaClient // 通过@EnableEurekaClient向服务中心注册
@EnableHystrix // 开启断路器
public class HystrixRibbonApplication {

	public static void main(String[] args) {
		SpringApplication.run(HystrixRibbonApplication.class, args);
	}
}
