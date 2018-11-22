package com.hz.learnboot.solr.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author hezhao
 * @Time 2018-07-22 22:01
 */
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class User {

    private Integer id;

    private String username;

    private String password;

    private String content;

}
