package com.hz.learnboot.jsp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * Spring Boot 应用启动类
 *
 * Spring Boot 部署到 外部Tomcat 中去启动
 * 注意：启动类需要继承自SpringBootServletInitializer，然后实现其configure()方法.
 *
 * 同时，也可以使用spring-boot:run和war两种方式启动
 *
 * Created by hezhao on 04/07/2018.
 */
@SpringBootApplication
public class SpringBootJspIntoTomcatApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(SpringBootJspIntoTomcatApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootJspIntoTomcatApplication.class, args);
    }
}
