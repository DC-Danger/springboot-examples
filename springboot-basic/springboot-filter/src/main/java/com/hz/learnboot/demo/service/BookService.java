package com.hz.learnboot.demo.service;

import com.hz.learnboot.demo.domain.Book;

import java.util.List;

/** Book 业务层
 * @Author hezhao
 * @Time 2018-06-30 16:12
 */
public interface BookService {
    /**
     * 获取所有 Book
     */
    List<Book> findAll();

}
