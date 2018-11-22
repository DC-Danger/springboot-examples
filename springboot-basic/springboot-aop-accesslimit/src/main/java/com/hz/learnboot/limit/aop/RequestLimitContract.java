package com.hz.learnboot.limit.aop;

import com.hz.learnboot.limit.annotaion.RequestLimit;
import com.hz.learnboot.limit.exception.RequestLimitException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 实现类
 *
 * Created by hezhao on 2018-07-24 11:40
 */
@Aspect
@Component
@Order(-1)
public class RequestLimitContract {

    private static final Logger logger = LoggerFactory.getLogger("RequestLimitLogger");
    private Map<String, Integer> localCache = new HashMap<>();

    @Before("within(@org.springframework.stereotype.Controller *) && @annotation(limit)")
    public void requestLimitController(final JoinPoint joinPoint, RequestLimit limit) throws RequestLimitException {
        limitImpl(joinPoint, limit);
    }

    @Before("within(@org.springframework.web.bind.annotation.RestController *) && @annotation(limit)")
    public void requestLimitRestController(final JoinPoint joinPoint, RequestLimit limit) throws RequestLimitException {
        limitImpl(joinPoint, limit);
    }

    public void limitImpl(final JoinPoint joinPoint, RequestLimit limit) throws RequestLimitException  {
        try {
            Object[] args = joinPoint.getArgs();
            HttpServletRequest request = null;
            for (int i = 0; i < args.length; i++) {
                if (args[i] instanceof HttpServletRequest) {
                    request = (HttpServletRequest) args[i];
                    break;
                }
            }
            if (request == null) {
                throw new RequestLimitException("方法中缺失HttpServletRequest参数");
            }
            String ip = request.getLocalAddr();
            String url = request.getRequestURL().toString();
            String key = "req_limit_".concat(url).concat(ip);
            if(localCache.get(key)==null || localCache.get(key)==0){
                localCache.put(key,1);
            }else{
                localCache.put(key, localCache.get(key)+1);
            }
            int count = localCache.get(key);
            if (count > 0) {
                Timer timer= new Timer();
                TimerTask task  = new TimerTask(){    // 创建一个新的计时器任务。
                    @Override
                    public void run() {
                        localCache.remove(key);
                    }
                };
                timer.schedule(task, limit.time());
                // 安排在指定延迟后执行指定的任务。task : 所要安排的任务。10000 : 执行任务前的延迟时间，单位是毫秒。
            }
            if (count > limit.count()) {
                logger.info("用户IP[" + ip + "]访问地址[" + url + "]超过了限定的次数[" + limit.count() + "]");
                throw new RequestLimitException();
            }
        } catch (RequestLimitException e) {
            throw e;
        } catch (Exception e) {
            logger.error("发生异常: ", e);
        }
    }

}
