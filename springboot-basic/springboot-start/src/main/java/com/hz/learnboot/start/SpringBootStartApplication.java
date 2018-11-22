package com.hz.learnboot.start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot 应用启动类
 *
 * Created by hezhao on 28/06/2018.
 */
// Spring Boot 应用的标识
// @SpringBootApplication是一个复合注解，包括@ComponentScan，和@SpringBootConfiguration，@EnableAutoConfiguration
@SpringBootApplication
public class SpringBootStartApplication {

	public static void main(String[] args) {
		// 程序启动入口
		// 启动嵌入式的 Tomcat,默认端口8080,并初始化 Spring 环境及其各 Spring 组件
		SpringApplication.run(SpringBootStartApplication.class, args);
	}
}
