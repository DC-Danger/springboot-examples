package com.hz.learnboot.demo;

import com.hz.learnboot.demo.filter.OperateFilter;
import com.hz.learnboot.demo.listener.MyServletContextListener2;
import com.hz.learnboot.demo.servlet.MyServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.DispatcherServlet;

import javax.annotation.PostConstruct;

/**
 * Spring Boot 应用启动类
 *
 * Created by hezhao on 28/06/2018.
 */
@SpringBootApplication
@ServletComponentScan // 自动扫描Servlet
public class SpringBootDemoApplication {

    private static final Logger logger = LoggerFactory.getLogger(SpringBootDemoApplication.class);

    // @PostConstruct注解的方法将会在依赖注入完成后被自动调用, 也可以理解为在spring容器初始化的时候执行该方法。
    @PostConstruct
    public void logTest() {
        logger.debug("@PostConstruct将在依赖注入完成后被自动调用...");
    }

    /**
     * 注册Filter
     * @return
     */
    @Bean
    public FilterRegistrationBean testFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setName("testFilter"); // Filter名称
        registration.setFilter(new OperateFilter()); // 具体实现的Filter对象
        registration.addUrlPatterns("/*"); // 匹配路径,可配置多个
        registration.addInitParameter("paramName", "paramValue"); // 初始化参数
        return registration;
    }

    /**
     * 注册Servlet
     */
    @Bean(name = "test")
    public ServletRegistrationBean servletRegistrationBean(@Autowired MyServlet myServlet) {
        // 匹配 /xs/* 下的任何url
        return new ServletRegistrationBean(myServlet, "/xs/*"); // ServletName默认值为首字母小写，即myServlet
    }

    /**
     * 注册WebListener
     *
     * @return
     */
    @Bean
    public ServletListenerRegistrationBean<MyServletContextListener2> servletListenerRegistrationBean() {
        ServletListenerRegistrationBean<MyServletContextListener2> servletListenerRegistrationBean = new ServletListenerRegistrationBean<>();
        servletListenerRegistrationBean.setListener(new MyServletContextListener2());
        return servletListenerRegistrationBean;
    }

    /**
     * 修改DispatcherServlet默认配置
     *
     * @param dispatcherServlet
     * @return
     */
     @Bean
     public ServletRegistrationBean dispatcherRegistration(DispatcherServlet dispatcherServlet) {
         ServletRegistrationBean registration = new ServletRegistrationBean(dispatcherServlet);
         registration.getUrlMappings().clear();
         registration.addUrlMappings("/");
         registration.addUrlMappings("*.do");
         registration.addUrlMappings("*.json");
         return registration;
     }

    public static void main(String[] args) {
        logger.debug("The SpringBootDemoApplication to start...");
        SpringApplication.run(SpringBootDemoApplication.class, args);
        logger.debug("The SpringBootDemoApplication has started...");
    }
}
