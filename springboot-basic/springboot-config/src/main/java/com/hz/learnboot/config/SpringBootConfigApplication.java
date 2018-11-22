package com.hz.learnboot.config;

import com.hz.learnboot.config.domain.BookProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;

/**
 * Spring Boot 应用启动类
 *
 * Created by hezhao on 28/06/2018.
 */
@SpringBootApplication
public class SpringBootConfigApplication {

    // @SpringBootConfiguration继承自@Configuration，二者功能也一致，标注当前类是配置类，
    // 并会将当前类内声明的一个或多个以@Bean注解标记的方法的实例纳入到srping容器中，并且实例名就是方法名。
    @Bean
    public Runnable createRunnable(){
        return () -> System.out.println("spring boot is running...");
    }

    @Bean
    public Map createMap(){
        Map map = new HashMap();
        map.put("username","zhangsan");
        map.put("age",27);
        return map;
    }

    public static void main(String[] args) {
        // 获取Spring上下文
        ApplicationContext context = SpringApplication.run(SpringBootConfigApplication.class, args);

        // 获取Bean
        context.getBean(Runnable.class).run();

        BookProperties book = context.getBean(BookProperties.class);
        System.out.println(book);

        Map map = (Map) context.getBean("createMap");
        int age = (int) map.get("age");
        System.out.println("age=="+age);
    }
}
