package com.hz.learnboot.async;

import com.hz.learnboot.async.service.AsyncTaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * Spring Boot 应用启动类
 *
 * Created by hezhao on 24/07/2018.
 */
@SpringBootApplication
public class Application {

    private static Logger logger = LoggerFactory.getLogger(AsyncTaskService.class);

	public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Application.class, args);
        AsyncTaskService service = context.getBean(AsyncTaskService.class);

        // 观察日志，可以看到我们两个任务是异步交替进行的。
        service.f1(); // 执行异步任务
        service.f2();
    }

}
