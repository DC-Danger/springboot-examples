package com.hz.learnboot.restdocs.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public class ResponseVo<T> {

    private int statusCode;
    private String message;
    private T data;

}
