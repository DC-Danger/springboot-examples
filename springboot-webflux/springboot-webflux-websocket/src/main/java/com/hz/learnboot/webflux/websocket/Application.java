package com.hz.learnboot.webflux.websocket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Spring Boot 应用启动类
 *
 * Created by hezhao on 28/06/2018.
 */
@SpringBootApplication
@Controller
public class Application {

	@GetMapping("/")
	public String index() {
		return "websocket-client.html";
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
