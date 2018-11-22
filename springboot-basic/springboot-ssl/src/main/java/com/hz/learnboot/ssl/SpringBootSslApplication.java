package com.hz.learnboot.ssl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Spring Boot 应用启动类
 *
 * Created by hezhao on 28/06/2018.
 */
@SpringBootApplication
@RestController
public class SpringBootSslApplication {

	@GetMapping("/")
	public String helloWorld() {
		return "Hello, world";
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSslApplication.class, args);
	}
}
