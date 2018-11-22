package com.hz.learnboot.demo.runner;

import com.hz.learnboot.demo.service.HelloWorldService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 服务启动执行
 *
 * CommandLineRunner 接口的 Component会在所有 Spring Beans 都初始化之后，SpringApplication.run() 之前执行，非常适合在应用程序启动之初进行一些数据初始化的工作。
 *
 */
@Component
@Order(1) // 执行顺序，数字越小优先级越高
public class MyStartupRunner1 implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(MyStartupRunner1.class);

	@Autowired
	private HelloWorldService helloWorldService;

	@Override
	public void run(String... args) throws Exception {
		logger.info(">>>>>>>>>>>>>>>服务启动执行，执行加载数据等操作 11111111 <<<<<<<<<<<<<");

		logger.info("############ " + this.helloWorldService.getHelloMessage());
	}

}
