package com.hz.learnboot.dubbo.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.rpc.RpcContext;
import com.hz.learnboot.dubbo.domain.Person;
import com.hz.learnboot.dubbo.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * Created by hezhao on 2018-07-13 18:12
 */
@RestController
@RequestMapping("/demo")
public class DemoController {

    // 引用Dubbo服务
    @Reference(
            version = "${demo.service.version}",
            application = "${dubbo.application.id}",
            registry = "${dubbo.registry.id}",
            url = "dubbo://localhost:20880",
            check = false,
            timeout = 2000,
            retries = 0,
            loadbalance = "leastactive"
    )
    private DemoService demoService;

    @GetMapping("/{name}/{age}")
    public Object demo(@PathVariable("name") String name, @PathVariable("age") Integer age) {
        // 隐式传参
        RpcContext.getContext().setAttachment("ThreadName", UUID.randomUUID().toString());

        Person person = Person.builder().name(name).age(age).build();
        String sayHello = demoService.sayHello(person);
        return sayHello;
    }
}
