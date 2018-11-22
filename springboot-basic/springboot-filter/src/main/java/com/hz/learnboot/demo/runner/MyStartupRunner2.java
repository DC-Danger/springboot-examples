package com.hz.learnboot.demo.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * 服务启动执行
 */
@Component
@Order(2) // 执行顺序,数字越小优先级越高
public class MyStartupRunner2 implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(MyStartupRunner2.class);

	@Value("${server.port}")
	private String myUrl;

	@Autowired
	private Environment env;


	@Override
	public void run(String... args) throws Exception {
		logger.info(">>>>>>>>>>>>>>>服务启动执行，执行加载数据等操作 22222222 <<<<<<<<<<<<<");

		// 测试实现EnvironmentAware接口，读取环境变量的方法。
		logger.info("开始读取配置信息...");
		logger.info(env.getProperty("JAVA_HOME"));
		logger.info(myUrl);
		String str = env.getProperty("server.port");
		logger.info(str);
	}

}
