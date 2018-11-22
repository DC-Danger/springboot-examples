package com.hz.learnboot.tkmybatis.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;

/**
 * 用户信息
 *
 * @Author hezhao
 * @Time 2018-06-30 22:37
 */
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class UserInfo {

    // 实体类必须用注解标明Id
    @Id
    private Long id;

    private String name;

    private String userName;

    private String password;

}
