package com.hz.learnboot.config.controller;

import com.hz.learnboot.config.domain.BookComponent;
import com.hz.learnboot.config.domain.BookProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** 书籍控制器
 *
 * @Author hezhao
 * @Time 2018-06-28 23:07
 */
@RestController
@RequestMapping("/api/book")
public class BookController {

    @Autowired
    private BookProperties bookProperties;

    @Autowired
    private BookComponent bookComponent;

    @GetMapping("/a")
    public BookProperties getBookA() {
        return bookProperties;
    }

    @GetMapping("/b")
    public BookComponent getBookB() {
        return bookComponent;
    }

}
