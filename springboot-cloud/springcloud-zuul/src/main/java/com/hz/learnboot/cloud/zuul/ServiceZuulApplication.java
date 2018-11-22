package com.hz.learnboot.cloud.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * Spring Boot 启动类
 *
 * Created by hezhao on 28/06/2018.
 */
@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy // 开启zuul
public class ServiceZuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceZuulApplication.class, args);
	}

}
