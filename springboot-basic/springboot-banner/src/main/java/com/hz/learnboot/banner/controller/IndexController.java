package com.hz.learnboot.banner.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/** 控制器
 *
 * @Author hezhao
 * @Time 2018-06-28 22:54
 */
@RestController
public class IndexController {

    private Logger logger = LoggerFactory.getLogger(IndexController.class);

    // 通过自动注入获取ApplicationContext
    @Autowired
    private ApplicationContext applicationContext;

    @RequestMapping(value = {"/", "/hello"},method = RequestMethod.GET)
    public String hello() {
        return "Welcome to Spring Boot !";
    }

    @RequestMapping("/exit")
    public String exit() {
        // 生命周期结束时,会执行`ExitCodeGenerator`接口的`getExitCode()`.这个接口也只有这一个方法.
        // 这个接口让我们在`SpringApplication`结束时,可以做一些我们自己想做的,或者必须要去做的工作,比如说数据库链接的关闭,IO流的关闭等等.
        SpringApplication.exit(applicationContext, () -> {
            // 自定义程序结束时返回的退出码
            logger.info("// the application exited.");
            return 1024;
        });
        return "ok";
    }

}
