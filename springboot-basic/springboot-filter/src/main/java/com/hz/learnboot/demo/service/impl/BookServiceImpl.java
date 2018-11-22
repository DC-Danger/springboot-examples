package com.hz.learnboot.demo.service.impl;

import com.hz.learnboot.demo.domain.Book;
import com.hz.learnboot.demo.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** Book 业务层实现
 * @Author hezhao
 * @Time 2018-06-30 16:13
 */
@Service
public class BookServiceImpl implements BookService {

    private static final Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);

    // 模拟数据库，存储 Book 信息
    private static Map<Long, Book> BOOK_DB = new HashMap<>();

    // 先初始化一些数据
    {
        Book book1 = Book.builder().id(1L).name("西游记").author("吴承恩").introduction("讲述师徒四人西天取经的故事。").build();
        Book book2 = Book.builder().id(2L).name("红楼梦").author("曹雪芹").introduction("古典爱情故事。").build();

        BOOK_DB.put(1L, book1);
        BOOK_DB.put(2L, book2);
    }

    @Override
    public List<Book> findAll() {
        return new ArrayList<>(BOOK_DB.values());
    }

}
