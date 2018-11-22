package com.hz.learnboot.cloud.eureka.impl;

import com.hz.learnboot.cloud.eureka.domain.Person;
import com.hz.learnboot.cloud.eureka.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * 服务实现
 *
 * Created by hezhao on 2018-07-13 17:17
 */
@Service
public class PersonServiceImpl implements PersonService {

    private static final Logger logger = LoggerFactory.getLogger(PersonServiceImpl.class);

    @Value("${server.port}")
    private String port;

    private static Map<Long,Person> map;

    // 初始化数据
    @PostConstruct
    public void initialPerson() {
        map = new HashMap<>();
        Person person;
        for (int i=1; i<=10; i++) {
            person = Person.builder()
                    .id((long) i)
                    .name("test" +i)
                    .age(20 + i)
                    .msg("from port " + port + ")")
                    .build();
            map.put((long) i, person);
        }
        logger.info("初始化Person数据完成。");
    }

    @Override
    public Person getPersonById(Long id) {
        Person person = map.get(id);
        logger.info("getPersonById->{}", person);
        return person;
    }
}
