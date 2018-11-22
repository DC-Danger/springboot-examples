package com.hz.learnboot.redis.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/** 用户信息
 * @Author hezhao
 * @Time 2018-06-30 22:37
 */
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class UserInfo implements Serializable {

    private static final long serialVersionUID = -399493304381305946L;

    private Long id;

    private String name;

    private String userName;

    private String password;

}
