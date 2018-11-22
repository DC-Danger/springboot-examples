package com.hz.learnboot.config.domain;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/** 书籍， 使用@Value注入属性
 * @Author hezhao
 * @Time 2018-06-29 0:50
 */
@Data @Builder @NoArgsConstructor @AllArgsConstructor
@Component
public class BookProperties {
    /** 书名 */
    @Value("${demo.book.name}")
    private String name;

    /**
     * 作者
     * 读取配置，如取不到默认值为：无名氏
     */
    @Value("${demo.book.author:无名氏}")
    private String author;

    /** 价格 */
    @Value("${demo.book.price}")
    private Double price;

    /** 是否售卖 */
    @Value("${demo.book.isSell}")
    private Boolean isSell;
}
