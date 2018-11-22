package com.hz.learnboot.dubbo;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * 启动服务
 *
 * Created by hezhao on 2018-07-13 17:29
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        // Spring Boot 非 Web 应用 方式启动
        new SpringApplicationBuilder(Application.class)
                .web(WebApplicationType.NONE)
                .run(args);
    }
}
