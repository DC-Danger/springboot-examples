package com.hz.learnboot.log;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

    private Logger logger = LoggerFactory.getLogger(ApplicationTests.class);

    @Test
    public void contextLoads() {
        Date now = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        logger.info(simpleDateFormat.format(now));
        logger.debug("-------DEBUG---------"); // 设置了level为info，所以debug级别的不会打印出来
        logger.warn("-------WARN---------");
        logger.error("time is {}.", now.getTime());
    }

}
