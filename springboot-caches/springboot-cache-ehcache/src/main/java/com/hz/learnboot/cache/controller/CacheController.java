package com.hz.learnboot.cache.controller;

import com.hz.learnboot.cache.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * RESTful API 接口测试
 *
 * @Author hezhao
 * @Time 2018-07-05 23:05
 */
@RestController
@RequestMapping(value = "/ehcache")
public class CacheController {
    @Autowired
    private CacheService cacheService;

    /**
     * 查询方法
     */
    @GetMapping
    public String getByCache() {
        Long startTime = System.currentTimeMillis();
        long timestamp = cacheService.getByCache();
        Long endTime = System.currentTimeMillis();
        System.out.println("耗时: " + (endTime - startTime));
        return timestamp + "";
    }

    /**
     * 保存方法
     */
    @GetMapping("/save")
    public void save() {
        cacheService.save();
    }

    /**
     * 删除方法
     */
    @GetMapping("/delete")
    public void delete() {
        cacheService.delete();
    }

}
