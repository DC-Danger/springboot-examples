package com.hz.learnboot.es.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 城市信息
 *
 * @Author hezhao
 * @Time 2018-06-30 23:53
 */
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class Person implements Serializable {
    private static final long serialVersionUID = -3322738983942005670L;

    /** Id */
    private String id;

    /** 姓名 */
    private String name;

    /** 年龄 */
    private Integer age;

    /** 修改日期 */
    private Date date;

}
