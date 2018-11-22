package com.hz.learnboot.dubbo.service.impl;

import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.dubbo.rpc.RpcException;
import com.hz.learnboot.dubbo.domain.Person;
import com.hz.learnboot.dubbo.service.DemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 服务实现
 *
 * Created by hezhao on 2018-07-13 17:17
 */
@Service
public class DemoServiceImpl implements DemoService {

    private static final Logger logger = LoggerFactory.getLogger(DemoServiceImpl.class);

    @Override
    public String sayHello(Person person) {
        // 获取隐式参数 可用来做框架通用参数透传，例如跟踪日志等
        logger.info("ThreadName:{}", RpcContext.getContext().getAttachment("ThreadName"));
        return "Hello, " + person.getName() + ", I'm  " + person.getAge() + " years old. (from Spring Boot)";
    }

}
