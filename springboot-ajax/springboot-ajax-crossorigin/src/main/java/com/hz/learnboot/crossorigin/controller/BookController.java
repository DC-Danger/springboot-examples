package com.hz.learnboot.crossorigin.controller;

import com.hz.learnboot.crossorigin.domain.Book;
import com.hz.learnboot.crossorigin.service.BookService;
import com.hz.learnboot.crossorigin.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
     */
    @CrossOrigin(origins = {"http://localhost:3000", "http://127.0.0.1:3000"}) // 设置方法允许跨域
    @GetMapping
    public Object getBookList() {
        List<Book> list = bookService.findAll();
        return ResultUtil.success(list);
    }

    /**
     * 根据 Id 获取 Book
     */
    @GetMapping("/{id}")
    public Object getUser(@PathVariable Long id) {
        Book book = bookService.findById(id);
        return ResultUtil.success(book);
    }

    /**
     * 创建 Book
     */
    @GetMapping("/create")
    public Object postBook(@ModelAttribute Book book) {
        return ResultUtil.success(bookService.insertByBook(book));
    }

    /**
     * 更新 Book
     */
    @GetMapping("/update")
    public Object putBook(@ModelAttribute Book book) {
        return ResultUtil.success(bookService.update(book));
    }

    /**
     * 删除 Book
     */
    @GetMapping("/delete/{id}")
    public Object deleteBook(@PathVariable Long id) {
        return ResultUtil.success(bookService.delete(id));
    }

}