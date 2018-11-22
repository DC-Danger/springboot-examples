package com.hz.learnboot.cache.service.jsr107;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by hezhao on 2018-07-06 16:32
 */
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class Person implements Serializable {
    private static final long serialVersionUID = 2331094838381858834L;

    private int ssn;
    private String firstName;
    private String lastName;
}
