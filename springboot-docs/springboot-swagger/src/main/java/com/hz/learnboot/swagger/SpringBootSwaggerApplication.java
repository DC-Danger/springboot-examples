package com.hz.learnboot.swagger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot 应用启动类
 *
 * 参考： https://github.com/zhangyd-c/springboot/tree/master/springboot-swagger
 *
 * 注意，此项目示例中，使用了三种ui依赖，每种依赖对应的访问页面不同：
 * - springfox-swagger-ui -> http://127.0.0.1:8082/swagger-ui.html
 * - swagger-bootstrap-ui -> http://127.0.0.1:8082/doc.html
 * - swagger-ui-layer -> http://127.0.0.1:8082/docs.html
 *
 * Created by hezhao on 28/06/2018.
 */
@SpringBootApplication
public class SpringBootSwaggerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSwaggerApplication.class, args);
	}
}
