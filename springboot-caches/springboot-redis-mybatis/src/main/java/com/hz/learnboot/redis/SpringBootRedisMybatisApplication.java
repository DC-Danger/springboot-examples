package com.hz.learnboot.redis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot 应用启动类
 *
 * Created by hezhao on 28/06/2018.
 */
@SpringBootApplication
// mapper 接口类扫描包配置
@MapperScan("com.hz.learnboot.redis.dao")
public class SpringBootRedisMybatisApplication {

	public static void main(String[] args) {
		// 程序启动入口
		// 启动嵌入式的 Tomcat 并初始化 Spring 环境及其各 Spring 组件
		SpringApplication.run(SpringBootRedisMybatisApplication.class, args);
	}
}
