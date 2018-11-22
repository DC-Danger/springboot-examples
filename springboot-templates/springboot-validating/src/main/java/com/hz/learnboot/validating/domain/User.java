package com.hz.learnboot.validating.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.io.Serializable;

/** 用户实体类
 * @Author hezhao
 * @Time 2018-06-29 9:50
 */
@Data @Builder @NoArgsConstructor @AllArgsConstructor
@Entity
public class User implements Serializable{
    private static final long serialVersionUID = 2266078595819790776L;

    /** 编号 */
    @Id
    @GeneratedValue
    private Long id;

    /** 名称 */
    @NotEmpty(message = "姓名不能为空")
    @Size(min = 2, max = 20, message = "姓名长度必须大于 2 且小于 20 字")
    private String name;

    /** 年龄 */
    @NotNull(message = "年龄不能为空")
    @Min(value = 0, message = "年龄大于 0")
    @Max(value = 300, message = "年龄不大于 300")
    private Integer age;

    /** 出生时间 */
    @NotEmpty(message = "出生时间不能为空")
    private String birthday;
}
