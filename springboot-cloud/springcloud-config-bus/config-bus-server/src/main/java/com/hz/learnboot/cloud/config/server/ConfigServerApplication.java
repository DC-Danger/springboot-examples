package com.hz.learnboot.cloud.config.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Spring Cloud Config Server 启动类
 *
 * 配置文件存放的三种方式：本地、Git仓库、SVN
 *
 * Created by hezhao on 28/06/2018.
 */
@SpringBootApplication
@EnableConfigServer // 开启配置服务器
@EnableEurekaClient // 通过@EnableEurekaClient向服务中心注册
public class ConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigServerApplication.class, args);
	}
}
