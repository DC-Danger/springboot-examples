package com.hz.learnboot.crossorigin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Spring Boot 应用启动类
 *
 * java语言在多数时，会作为一个后端语言，为前端的html,node.js等提供API接口。前端通过ajax请求去调用java的API服务。
 * 以node.js为例，介绍两种跨域方式：CrossOrigin和反向代理。
 *
 * 参考：https://www.cnblogs.com/GoodHelper/p/6824562.html
 *
 * Created by hezhao on 28/06/2018.
 */
@SpringBootApplication
public class SpringBootCrossOriginApplication {

    // 实现全局跨域
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                // registry.addMapping("/**")：为根目录的全部请求，也可以设置为"/user/**"，这意味着是user目录下的所有请求。
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:3000")
                        .allowedOrigins("http://127.0.0.1:3000");
            }
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootCrossOriginApplication.class, args);
    }
}
