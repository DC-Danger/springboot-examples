package com.hz.learnboot.jdbc.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 用户信息
 * @Author hezhao
 * @Time 2018-06-30 22:37
 */
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class UserInfo {

    private Long id;

    private String name;

    private String userName;

    private String password;

}
