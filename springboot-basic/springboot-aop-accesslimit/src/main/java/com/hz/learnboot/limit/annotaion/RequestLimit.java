package com.hz.learnboot.limit.annotaion;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import java.lang.annotation.*;

/**
 * 访问次数限制注解
 *
 * Created by hezhao on 2018-07-24 11:39
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
@Order(Ordered.HIGHEST_PRECEDENCE) // 最高优先级
public @interface RequestLimit {

    /**
     * 允许访问的次数，默认值MAX_VALUE
     */
    int count() default Integer.MAX_VALUE;

    /**
     * 时间段，单位为毫秒，默认值一分钟
     */
    long time() default 60000;
}