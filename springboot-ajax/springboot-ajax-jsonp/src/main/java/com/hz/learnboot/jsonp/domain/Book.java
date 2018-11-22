package com.hz.learnboot.jsonp.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/** 书籍实体类
 * @Author hezhao
 * @Time 2018-06-30 15:50
 */
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class Book implements Serializable{
    private static final long serialVersionUID = 2266078595819790776L;

    /** 编号 */
    private Long id;

    /** 书名 */
    private String name;

    /** 作者 */
    private String author;

    /** 简介 */
    private String introduction;
}