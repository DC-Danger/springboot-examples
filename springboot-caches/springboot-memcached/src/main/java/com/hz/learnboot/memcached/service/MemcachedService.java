package com.hz.learnboot.memcached.service;

import com.danga.MemCached.MemCachedClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Memcached服务类
 *
 * Created by hezhao on 2018-08-03 17:31
 */
@Service
public class MemcachedService {

    private Logger logger = LoggerFactory.getLogger(MemcachedService.class);

    @Autowired
    private MemCachedClient memCachedClient;

    @PostConstruct
    public void setEncoding() {
        // 设置默认编码
        memCachedClient.setDefaultEncoding("UTF-8");
    }

    /**
     * 获取元素
     *
     * @param key
     * @return
     */
    public Object get(String key) {
        boolean keyExists = memCachedClient.keyExists(key);

        // 如果元素存在，直接从缓存中取出返回
        if (keyExists) {
            logger.info("元素[{}]存在", key);
            return memCachedClient.get(key);
        }

        // 这里模拟耗时操作
        try {
            Thread.sleep(3 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        logger.info("元素[{}]不存在，现在保存", key);
        long time = new Timestamp(System.currentTimeMillis()).getTime();
        // 缓存元素
        set(key, time, 3000L);
        return time;
    }

    /**
     * 保存元素, 永不过期
     *
     * @param key 键
     * @param value 值
     * @return
     */
    public boolean set(String key, Object value) {
        return set(key, value, null);
    }

    /**
     * 保存元素
     *
     * @param key 键
     * @param value 值
     * @param timeout 过期时间，毫秒
     * @return
     */
    public boolean set(String key, Object value, Long timeout) {
        logger.info("进行缓存：key[{}], value[{}], timeout[{}]", key, value, timeout);
        // 永不过期
        if (timeout == null) {
            return memCachedClient.set(key, value);
        }
        return memCachedClient.set(key, value, new Date(timeout));
    }

    /**
     * 删除元素
     *
     * @param key 键
     */
    public boolean delete(String key) {
        logger.info("删除缓存：[{}]", key);
        return memCachedClient.delete(key);
    }

}
