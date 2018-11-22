package com.hz.learnboot.redis.util;

import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/** 字符串存取
 * Created by hezhao on 2018-07-05 09:11
 */
@Repository
public class RedisStrOperate {

    @Resource(name="stringRedisTemplate")
    protected ValueOperations<String, String> valueOperations;

    /**
     * 存入字符串
     * @param key
     * @param value
     */
    public void setStr(String key, String value){
        valueOperations.set(key, value);
    }

    /**
     * 取出字符串
     * @param key
     * @return
     */
    public String getStr(String key){
        return valueOperations.get(key);
    }
}
