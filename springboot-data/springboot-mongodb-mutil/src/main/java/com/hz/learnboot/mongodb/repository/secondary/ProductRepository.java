package com.hz.learnboot.mongodb.repository.secondary;

import com.hz.learnboot.mongodb.domain.secondary.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * 商品 Repository 接口类
 *
 * @Author hezhao
 * @Time 2018-06-30 23:59
 */
public interface ProductRepository extends MongoRepository<Product, String> {

    // 继承了 MongoRepository 会默认实现很多基本的增删改查，省了很多自己写dao层的代码
    
}
