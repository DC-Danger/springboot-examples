package com.hz.learnboot.cloud.eureka.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Spring Cloud Eureka Server 启动类
 *
 * Created by hezhao on 28/06/2018.
 */
@SpringBootApplication
@EnableEurekaServer // 启动服务注册中心
public class EurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaServerApplication.class, args);
	}
}
