package com.hz.learnboot.cloud.eureka.service;

import com.hz.learnboot.cloud.eureka.domain.Person;

/**
 * Person服务接口
 *
 * Created by hezhao on 2018-07-13 17:03
 */
public interface PersonService {

    Person getPersonById(Long id);

}
