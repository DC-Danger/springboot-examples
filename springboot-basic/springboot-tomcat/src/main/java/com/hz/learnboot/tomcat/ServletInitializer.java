package com.hz.learnboot.tomcat;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * Spring Boot 部署到 外部Tomcat 中去启动
 * 继承自SpringBootServletInitializer，然后实现其configure()方法.
 */
public class ServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringTomcatApplication.class);
    }

}
