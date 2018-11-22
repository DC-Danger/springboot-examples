package com.hz.learnboot.task.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Cron表达式实体类
 *
 * Created by hezhao on 2018-07-11 20:41
 */
@Data @Builder @NoArgsConstructor @AllArgsConstructor
@Entity
public class Cron {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String key;

    private String cron;

}
