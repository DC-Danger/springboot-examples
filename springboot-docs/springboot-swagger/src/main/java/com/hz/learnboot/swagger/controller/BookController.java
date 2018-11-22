package com.hz.learnboot.swagger.controller;

import com.hz.learnboot.swagger.domain.Book;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/** Book控制器
 *
 * @Author hezhao
 * @Time 2018-06-28 23:07
 */
@RestController
@RequestMapping(value = "/book")
@Api(value = "书籍接口",description="关于书籍的增删改查接口")
public class BookController {

    @ApiOperation("获取书籍信息列表")
    @GetMapping
    public List<Book> getBookList() {
        Book book = Book.builder().id(1L).name("西游记").author("吴承恩").introduction("讲述师徒四人西天取经的故事。").build();
        return Arrays.asList(book);
    }

}