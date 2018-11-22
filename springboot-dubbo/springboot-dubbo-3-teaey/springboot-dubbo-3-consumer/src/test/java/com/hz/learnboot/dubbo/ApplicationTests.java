package com.hz.learnboot.dubbo;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.rpc.RpcContext;
import com.hz.learnboot.dubbo.domain.Person;
import com.hz.learnboot.dubbo.service.DemoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

    @Reference(
            version = "1.0.0",
            url = "dubbo://localhost:20880",
            check = false,
            timeout = 2000
    )
    private DemoService demoService;

    @Test
    public void contextLoads() {
        // 隐式传参
        RpcContext.getContext().setAttachment("ThreadName", UUID.randomUUID().toString());

        Person person = Person.builder().name("艾编程").age(23).build();
        String sayHello = demoService.sayHello(person);
        System.out.println(sayHello);
    }

}
