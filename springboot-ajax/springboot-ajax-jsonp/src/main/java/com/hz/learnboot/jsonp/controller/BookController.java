package com.hz.learnboot.jsonp.controller;

import com.hz.learnboot.jsonp.domain.Book;
import com.hz.learnboot.jsonp.service.BookService;
import com.hz.learnboot.jsonp.util.JsonpUtil;
import com.hz.learnboot.jsonp.util.ResultUtil;
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
    @GetMapping
    public Object getBookList(@RequestParam(required = false) String callback) {
        List<Book> list = bookService.findAll();
        return JsonpUtil.callbackStr(callback, ResultUtil.success(list));
    }

    /**
     * 根据 Id 获取 Book
     */
    @GetMapping("/{id}")
    public Object getUser(@PathVariable Long id, @RequestParam(required = false) String callback) {
        Book book = bookService.findById(id);
        return JsonpUtil.callbackStr(callback, ResultUtil.success(book));
    }

    /**
     * 创建 Book
     */
    @GetMapping("/create")
    public Object postBook(@ModelAttribute Book book, @RequestParam(required = false) String callback) {
        return JsonpUtil.callbackStr(callback, ResultUtil.success(bookService.insertByBook(book)));
    }

    /**
     * 更新 Book
     */
    @GetMapping("/update")
    public Object putBook(@ModelAttribute Book book, @RequestParam(required = false) String callback) {
        return JsonpUtil.callbackStr(callback, ResultUtil.success(bookService.update(book)));
    }

    /**
     * 删除 Book
     */
    @GetMapping("/delete/{id}")
    public Object deleteBook(@PathVariable Long id, @RequestParam(required = false) String callback) {
        return JsonpUtil.callbackStr(callback, ResultUtil.success(bookService.delete(id)));
    }

}