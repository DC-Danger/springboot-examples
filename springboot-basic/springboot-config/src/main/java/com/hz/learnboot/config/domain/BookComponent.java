package com.hz.learnboot.config.domain;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/** 书籍， 使用ConfigurationProperties注入属性
 * @Author hezhao
 * @Time 2018-06-29 0:49
 */
@Data @Builder @NoArgsConstructor @AllArgsConstructor
@Component
@ConfigurationProperties(prefix = "demo.book") // 以demo.book开头的属性
@Validated
public class BookComponent {
    /** 书名 */
    @NotEmpty
    private String name;

    /** 作者 */
    @NotNull
    private String author;

    /** 价格 */
    @NotNull
    private Double price;

    /** 是否售卖 */
    @NotNull
    private Boolean isSell;
}
