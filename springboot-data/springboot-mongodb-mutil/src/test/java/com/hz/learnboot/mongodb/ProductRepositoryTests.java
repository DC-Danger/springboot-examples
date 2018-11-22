package com.hz.learnboot.mongodb;

import com.hz.learnboot.mongodb.domain.primary.City;
import com.hz.learnboot.mongodb.domain.secondary.Product;
import com.hz.learnboot.mongodb.repository.primary.CityRepository;
import com.hz.learnboot.mongodb.repository.secondary.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

/**
 * Created by hezhao on 2018-07-05 11:09
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductRepositoryTests {
    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testSave() {
        Product product = Product.builder().id("1").name("华硕笔记本").build();
        // 插入
        productRepository.insert(product);
    }

    @Test
    public void testFindAll() {
        List<Product> productList = productRepository.findAll();

        System.out.println("==================================testFindAll==================================");
        System.out.println(productList);
        System.out.println("==================================testFindAll==================================");
    }

    @Test
    public void testFindById() {
        Optional<Product> productOptional = productRepository.findById("1");

        System.out.println("==================================testFindById==================================");
        System.out.println(productOptional.orElse(null));
        System.out.println("==================================testFindById==================================");
    }

}
