package com.hz.learnboot.memcached.controller;

import com.hz.learnboot.memcached.service.MemcachedService;
import com.hz.learnboot.memcached.util.EncodingUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 缓存测试
 *
 * @Author hezhao
 * @Time 2018-08-03 23:05
 */
@RestController
@RequestMapping(value = "/memcached")
public class MemcachedController {

    private Logger logger = LoggerFactory.getLogger(MemcachedController.class);

    @Autowired
    private MemcachedService memcachedService;

    /**
     * 查询方法
     */
    @GetMapping(value = "/{key}", produces = "application/json;charset=UTF-8")
    public Object get(@PathVariable("key") String key) {
        key = EncodingUtil.convertEncoding(key);

        Long startTime = System.currentTimeMillis();
        Object obj = memcachedService.get(key);
        Long endTime = System.currentTimeMillis();
        logger.info("耗时: " + (endTime - startTime));
        return obj;
    }

    /**
     * 保存方法
     */
    @GetMapping("/save/{key}/{value}")
    public Object save(@PathVariable("key") String key, @PathVariable("value") String value) {
        key = EncodingUtil.convertEncoding(key);
        value = EncodingUtil.convertEncoding(value);
        return memcachedService.set(key, value);
    }

    /**
     * 保存方法, 带过期时间
     */
    @GetMapping("/save/{key}/{value}/{timeout}")
    public Object save(@PathVariable("key") String key, @PathVariable("value") String value, @PathVariable("timeout") Long timeout) {
        key = EncodingUtil.convertEncoding(key);
        value = EncodingUtil.convertEncoding(value);
        return memcachedService.set(key, value, timeout);
    }

    /**
     * 删除方法
     */
    @GetMapping("/delete/{key}")
    public Object delete(@PathVariable("key") String key) {
        key = EncodingUtil.convertEncoding(key);
        return memcachedService.delete(key);
    }

}
