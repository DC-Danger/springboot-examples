package com.hz.learnboot.demo.controller;

import com.hz.learnboot.demo.domain.Book;
import com.hz.learnboot.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/** Book控制器
 *
 * @Author hezhao
 * @Time 2018-06-28 23:07
 */
@RestController
@RequestMapping(value = "/book")
public class BookController {

    @Autowired
    private BookService bookService;

    /**
     * 获取 Book 列表
     * 处理 "/book" 的 GET 请求，用来获取 Book 列表
     */
    @GetMapping({"", "/list"})
    public List<Book> getBookList() {
        return bookService.findAll();
    }

}