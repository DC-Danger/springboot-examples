package com.hz.learnboot.redis.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.connection.DefaultStringRedisConnection;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.StringRedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisConnectionUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/** redis操作接口
 * @author hezhao
 */
@Service
public class RedisOperate {

    public static Logger logger = LoggerFactory.getLogger(RedisOperate.class);

    /***
     * 缓存key的前缀
     */
    private static final String PREFIX = "demo_";

    @Autowired
    @Qualifier("jedisConnectionFactory")
    private JedisConnectionFactory jedisConnFactory;

    /***
     * 插入数据到redis,使用set命令
     *
     * @param key 键值
     * @param value 内容
     * @param timeout 超时时间秒
     * @return 0成功 -1失败
     */
    public int setRedisStr(String key, String value, long timeout) {
        RedisConnection redisConnection = null;
        try {
            redisConnection = jedisConnFactory.getConnection();
            StringRedisConnection stringRedisConnection = new DefaultStringRedisConnection(redisConnection);
            // key加前缀名
            key = PREFIX + key;
            if (redisConnection != null) {
                // 设置数据到redis
                stringRedisConnection.set(key, value);
                // 设置过期时间
                if(timeout > 0){
                    stringRedisConnection.expire(key, timeout);
                }
                
                return 1;
            }
        } catch (Exception e) {
            logger.error("insertRedisStr key:{},value:{} error.", new Object[] { key, value }, e);
        } finally {
            RedisConnectionUtils.releaseConnection(redisConnection, jedisConnFactory);
        }

        return -1;
    }

    /***
     * 从redis中查询数据,使用get命令
     *
     * @param key 键值
     * @return
     */
    public String getRedisStr(String key) {
        RedisConnection redisConnection = null;
        try {
            redisConnection = jedisConnFactory.getConnection();
            StringRedisConnection stringRedisConnection = new DefaultStringRedisConnection(redisConnection);
            // key加前缀名
            key = PREFIX + key;
            if (redisConnection != null) {
                // 从redis查询数据
                return stringRedisConnection.get(key);
            }
        } catch (Exception e) {
            logger.error("queryRedisStr key:{}= error.", key, e);
        } finally {
            RedisConnectionUtils.releaseConnection(redisConnection, jedisConnFactory);
        }

        return null;
    }

    /***
     * 删除redis中数据
     *
     * @param key 键值
     * @return 0成功-1失败
     */
    public int delRedisStr(String key) {
        RedisConnection redisConnection = null;
        try {
            redisConnection = jedisConnFactory.getConnection();
            StringRedisConnection stringRedisConnection = new DefaultStringRedisConnection(redisConnection);
            // key加前缀名
            key = PREFIX + key;
            if (stringRedisConnection != null) {
                // 从redis查询数据
                stringRedisConnection.del(key);
                return 1;
            }
        } catch (Exception e) {
            logger.error("queryRedisStr key:{}= error.", key, e);
        } finally {
            RedisConnectionUtils.releaseConnection(redisConnection, jedisConnFactory);
        }

        return -1;
    }
    
    /***
     * 设置数据到redis hash表中,命令hset
     *
     * @param key 键值
     * @param field hash键值
     * @param value 内容
     * @param timeout 超时时间
     * @return 1成功-1失败
     */
    public int insertRedisHash(String key, String field, String value, long timeout) {
        RedisConnection redisConnection = null;
        try {
            redisConnection = jedisConnFactory.getConnection();
            StringRedisConnection stringRedisConnection = new DefaultStringRedisConnection(redisConnection);
            // key加前缀名
            key = PREFIX + key;
            if (stringRedisConnection != null) {
                // hset命令
                stringRedisConnection.hSet(key, field, value);
                if (timeout > 0) {
                    // 设置超时时间
                    stringRedisConnection.expire(key, timeout);
                }
                return 1;
            }
        } catch (Exception e) {
            logger.error("queryRedisStr key:{}= error.", key, e);
        } finally {
            RedisConnectionUtils.releaseConnection(redisConnection, jedisConnFactory);
        }

        return -1;
    }

    /***
     * 从redis hash表中取值,hget命令
     *
     * @param key 键值
     * @param field hash键值
     * @return
     */
    public String queryRedisHash(String key, String field) {
        RedisConnection redisConnection = null;
        try {
            redisConnection = jedisConnFactory.getConnection();
            StringRedisConnection stringRedisConnection = new DefaultStringRedisConnection(redisConnection);
            // key加前缀名
            key = PREFIX + key;

            if (stringRedisConnection != null) {
                // hget命令
                return stringRedisConnection.hGet(key, field);
            }
        } catch (Exception e) {
            logger.error("queryRedisStr key:{}= error.", key, e);
        } finally {
            RedisConnectionUtils.releaseConnection(redisConnection, jedisConnFactory);
        }

        return null;
    }

    /***
     * 删除redis hash表中数据
     *
     * @param key 键值
     * @param field hash键值
     * @return 1成功-1失败
     */
    public int deleteRedisHash(String key, String field) {
        RedisConnection redisConnection = null;
        try {
            redisConnection = jedisConnFactory.getConnection();
            StringRedisConnection stringRedisConnection = new DefaultStringRedisConnection(redisConnection);
            // key加前缀名
            key = PREFIX + key;
            if (redisConnection != null) {
                stringRedisConnection.hDel(key, field);
                return 1;
            }
        } catch (Exception e) {
            logger.error("insertcache error:", e);
        } finally {
            RedisConnectionUtils.releaseConnection(redisConnection, jedisConnFactory);
        }
        return -1;
    }
    
    /***
     * hMset命令,设置map
     *
     * @param key 键值
     * @param map map对象
     * @param timeout 超时时间,单位秒
     * @return
     */
    public int insertRedisMap(String key, Map<String, String> map, long timeout) {
        RedisConnection redisConnection = null;
        try {
            redisConnection = jedisConnFactory.getConnection();
            StringRedisConnection stringRedisConnection = new DefaultStringRedisConnection(redisConnection);
            // key加前缀名
            key = PREFIX + key;
            if (stringRedisConnection != null) {
                // hMset命令
                stringRedisConnection.hMSet(key, map);
                if (timeout > 0) {
                    // 设置超时时间
                    stringRedisConnection.expire(key, timeout);
                }
                return 1;
            }
        } catch (Exception e) {
            logger.error("insertRedisMap key:{},map:{} error", key, map);
            logger.error("insertRedisMap error.", e);
        } finally {
            RedisConnectionUtils.releaseConnection(redisConnection, jedisConnFactory);
        }

        return -1;
    }
    
    /***
     * 从redis hash表中取值,hmget命令
     *
     * @param key 键值
     * @param field hash键值
     * @return
     */
    public String queryRedisMap(String key, String field) {
        RedisConnection redisConnection = null;
        try {
            redisConnection = jedisConnFactory.getConnection();
            StringRedisConnection stringRedisConnection = new DefaultStringRedisConnection(redisConnection);
            // key加前缀名
            key = PREFIX + key;

            if (stringRedisConnection != null) {
                // hget命令
                List<String> lists = stringRedisConnection.hMGet(key, field);
                if (lists != null && !lists.isEmpty()) {
                    // 如果有多条,则取第一条
                    return lists.get(0);
                }
            }
        } catch (Exception e) {
            logger.error("queryRedisStr key:{}= error.", key, e);
        } finally {
            RedisConnectionUtils.releaseConnection(redisConnection, jedisConnFactory);
        }

        return null;
    }
    
    /***
     * 检查redis中key值是否存在
     * @param key
     * @return
     */
    public boolean exists(String key){
        RedisConnection redisConnection = null;
        try {
            redisConnection = jedisConnFactory.getConnection();
            StringRedisConnection stringRedisConnection = new DefaultStringRedisConnection(redisConnection);
            // key加前缀名
            key = PREFIX + key;

            if (stringRedisConnection != null) {
                // exists命令
                return stringRedisConnection.exists(key);
            }
        } catch (Exception e) {
            logger.error("queryRedisStr key:{}= error.", key, e);
        } finally {
            RedisConnectionUtils.releaseConnection(redisConnection, jedisConnFactory);
        }
        
        return false;
    }
    
    /***
     * 自增,redis的CAS操作
     * @param key redis中key值
     * @param inc 每次自增值
     * @return
     */
    public Long increment(String key, int inc){
        RedisConnection redisConnection = null;
        try {
            redisConnection = jedisConnFactory.getConnection();
            StringRedisConnection stringRedisConnection = new DefaultStringRedisConnection(redisConnection);
            // key加前缀名
            key = PREFIX + key;

            if (stringRedisConnection != null) {
                // incrBy自增命令
                return stringRedisConnection.incrBy(key, inc);
            }
        } catch (Exception e) {
            logger.error("queryRedisStr key:{}= error.", key, e);
        } finally {
            RedisConnectionUtils.releaseConnection(redisConnection, jedisConnFactory);
        }
        
        return null;
    }
}
