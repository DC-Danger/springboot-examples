package com.hz.learnboot.dubbo;

import com.hz.learnboot.dubbo.domain.Person;
import com.hz.learnboot.dubbo.service.DemoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

    @Autowired
    private DemoService demoService;

    @Test
    public void contextLoads() {
        Person person = Person.builder().name("艾编程").age(23).build();
        String sayHello = demoService.sayHello(person);
        System.out.println(sayHello);
    }

}
