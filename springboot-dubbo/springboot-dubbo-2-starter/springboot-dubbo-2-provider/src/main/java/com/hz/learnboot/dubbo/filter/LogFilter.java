package com.hz.learnboot.dubbo.filter;

import com.alibaba.dubbo.rpc.*;
import com.alibaba.fastjson.JSON;
import org.apache.logging.log4j.ThreadContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.UUID;

/**
 * 日志过滤器
 *
 * Created by hezhao on 2018-07-13 17:17
 */
public class LogFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(LogFilter.class);

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        Result result = null;
        long t1 = 0L;
        try {
            String traceId = ThreadContext.get("traceid");
            if(StringUtils.isEmpty(traceId)){
                ThreadContext.put("traceid", UUID.randomUUID().toString().replace("-", ""));
                logger.info("Class:{},Method:{},Param:{}", invocation.getClass().getName(),invocation.getMethodName(), invocation.getArguments());
            }
            t1 = System.currentTimeMillis();
            result = invoker.invoke(invocation);
        } catch (Throwable e) {
            logger.error("Server Exception", e);
        } finally {
            logger.info("cost:{}, result:{}, exception:{}", System.currentTimeMillis() - t1, JSON.toJSONString(result.getValue()), JSON.toJSONString(result.getException()));
            ThreadContext.remove("traceid");
        }

        return result;
    }

}
