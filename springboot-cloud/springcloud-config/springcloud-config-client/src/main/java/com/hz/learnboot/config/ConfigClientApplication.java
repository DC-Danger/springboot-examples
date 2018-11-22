package com.hz.learnboot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Spring Boot 启动类
 *
 * 注意事项:
 * 1、config配置文件名称必须是：bootstrap.yml 或 bootstrap.properties
 * 2、客户端的spring.application.name配置是和Git服务器上面的文件名相对应的，如果你的客户端是其他名字就报错找不到参数。
 * 3、客户端加载到的配置文件的配置项会覆盖本项目已有配置，比如客户端你自己配置的端口是8881，但是如果读取到config-clent-dev这个配置文件中也有配置端口为8882，那么此时客户端访问的地址应该是8882
 *
 * Created by hezhao on 28/06/2018.
 */
@SpringBootApplication
@RestController
public class ConfigClientApplication {

	@Value("${name}")
	private String name;

	@GetMapping("/")
	public String getName(){
		return name;
	}

	public static void main(String[] args) {
		SpringApplication.run(ConfigClientApplication.class, args);
	}
}
