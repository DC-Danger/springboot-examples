package com.hz.learnboot.cache.dao;

import com.hz.learnboot.cache.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

/** Book 持久层操作接口
 *
 * 没有任何配置信息，将会使用H2内存数据库模式
 *
 * Created by hezhao on 2018-07-05 09:16
 */
public interface BookRepository extends JpaRepository<Book, Long> {

}
