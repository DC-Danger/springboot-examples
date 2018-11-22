package com.hz.learnboot.restdocs.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class User {

    private Integer id;
    private String name;
    private Integer age;
    private Date birthday;

}
