package com.hz.learnboot.cache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * Spring Boot 应用启动类
 *
 * Created by hezhao on 28/06/2018.
 */
@SpringBootApplication
@EnableCaching // 在 Spring Boot 中使用 @EnableCaching 开启缓存支持。
public class SpringBootCacheApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootCacheApplication.class, args);
	}
}
