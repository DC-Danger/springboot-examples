package com.hz.learnboot.cloud.eureka.service;

import com.hz.learnboot.cloud.eureka.domain.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 熔断实现类，需要实现PersonFeignService接口
 *
 * Created by hezhao on 2018-08-01 17:55
 */
@Component
public class SchedualServicePersonHystric implements PersonFeignService {

    private Logger logger = LoggerFactory.getLogger(SchedualServicePersonHystric.class);

    @Override
    public Person getPersonById(Long id) {
        logger.error("熔断方法SchedualServicePersonHystric.getPersonById()被调用！ id:{}", id);
        return null;
    }
}
