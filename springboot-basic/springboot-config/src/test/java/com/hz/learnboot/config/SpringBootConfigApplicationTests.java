package com.hz.learnboot.config;

import com.hz.learnboot.config.controller.BookController;
import com.hz.learnboot.config.controller.CustomerController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootConfigApplicationTests {

    @Autowired
    private BookController bookController;
    @Autowired
    private CustomerController customerController;

    @Test
    public void testGetBook() {
        System.out.println("==================================testGetBook==================================");
        System.out.println(bookController.getBookA());
        System.out.println(bookController.getBookB());
        System.out.println("==================================testGetBook==================================");
    }

    @Test
    public void testCustomer() {
        System.out.println("==================================testCustomer==================================");
        System.out.println(customerController.index());
        System.out.println("==================================testCustomer==================================");
    }

}
