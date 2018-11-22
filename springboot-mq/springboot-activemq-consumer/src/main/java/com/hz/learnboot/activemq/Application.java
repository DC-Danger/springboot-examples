package com.hz.learnboot.activemq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * Spring Boot 应用启动类
 *
 * Created by hezhao on 24/07/2018.
 */
@SpringBootApplication
@EnableAsync // 开启对Async的支持,否则异步任务不启作用
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
