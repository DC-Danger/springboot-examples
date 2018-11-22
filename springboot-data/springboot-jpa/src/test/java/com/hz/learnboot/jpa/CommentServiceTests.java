package com.hz.learnboot.jpa;

import com.hz.learnboot.jpa.service.CommentService;
import lombok.val;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author hezhao
 * @Time 2018-07-01 22:26
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CommentServiceTests {

    @Autowired
    private CommentService commentService;

    @Test
    public void testSearchComment(){
        val list = commentService.searchComment(null,"特金会", null, null, null, null);
        System.out.println("==================================testSearchComment==================================");
        list.forEach(System.out::println);
        System.out.println("==================================testSearchComment==================================");
    }

}
