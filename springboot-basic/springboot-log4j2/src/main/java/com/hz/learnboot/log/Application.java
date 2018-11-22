package com.hz.learnboot.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Spring Boot 应用启动类
 *
 * Created by hezhao on 28/06/2018.
 */
@SpringBootApplication
@EnableScheduling
public class Application {

	private static Logger logger = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		logger.info("application start...");
		SpringApplication.run(Application.class, args);
		logger.info("application id started...");
	}
}
