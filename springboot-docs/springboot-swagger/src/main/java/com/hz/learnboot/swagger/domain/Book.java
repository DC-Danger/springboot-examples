package com.hz.learnboot.swagger.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 书籍实体类
 *
 * @Author hezhao
 * @Time 2018-06-30 15:50
 */
@Data @Builder @NoArgsConstructor @AllArgsConstructor
@ApiModel(value="model", description = "书籍实体")
public class Book implements Serializable{
    private static final long serialVersionUID = 2266078595819790776L;

    @ApiModelProperty(value="书籍编号")
    private Long id;

    @ApiModelProperty(value="书名")
    private String name;

    @ApiModelProperty(value="作者")
    private String author;

    @ApiModelProperty(value="简介")
    private String introduction;
}