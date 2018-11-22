package com.hz.learnboot.es.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 博客实体类
 *
 * @Author hezhao
 * @Time 2018-07-22 12:43
 */
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class CsdnBlog {

    /** 标题 */
    private String title;

    /** 内容 */
    private String content;

    /** 浏览数 */
    private Integer view;

    /** 标签 */
    private String tag;

    /** 作者 */
    private String author;

    /** 日期 */
    private String date;

    public void print() {
        System.out.println("标题：" + getTitle());
        System.out.println("内容：" + getContent());
        System.out.println("浏览数：" + getView());
        System.out.println("标签：" + getTag());
        System.out.println("作者：" + getAuthor());
    }
}
