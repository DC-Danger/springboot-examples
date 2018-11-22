package com.hz.learnboot.redismq.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Jedis工厂类， 非切片(非分布式单机)
 *
 * Created by hezhao on 2018-07-24 17:00
 */
public class JedisFactory {

    // Redis服务器IP
    private static String HOST = "127.0.0.1";

    // Redis的端口号
    private static int PORT = 6379;

    // 访问密码
    // private static String AUTH = "admin";

    // 可用连接实例的最大数目，默认值为8；
    // 如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
    private static int MAX_ACTIVE = 1024;

    // 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
    private static int MAX_IDLE = 200;

    // 等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
    private static int MAX_WAIT = 10000;

    // 连接池中的最小空闲连接
    private static int MIN_IDLE = 20;

    //  超时时间
    private static int TIMEOUT = 10000;

    // 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
    private static boolean TEST_ON_BORROW = true;

    // 非切片连接池
    private static JedisPool jedisPool = null;

    /**
     * 初始化Redis连接池，非切片
     */
    static {
        try {
            // 池基本配置
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(MAX_ACTIVE);
            config.setMaxIdle(MAX_IDLE);
            config.setMaxWaitMillis(MAX_WAIT);
            config.setMinIdle(MIN_IDLE);
            config.setTestOnBorrow(TEST_ON_BORROW);

            // jedisPool = new JedisPool(config, ADDR, PORT, TIMEOUT, AUTH);
            jedisPool = new JedisPool(config, HOST, PORT, TIMEOUT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取Jedis实例，非切片
     * 
     * @return
     */
    public synchronized static Jedis getJedis() {
        try {
            if (jedisPool != null) {
                Jedis resource = jedisPool.getResource();
                return resource;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 关闭连接池，非切片
     */
    public static void close() {
        jedisPool.close();
    }

    /**
     * 关闭Jedis非切片客户端
     * @param jedis
     */
    public void close(Jedis jedis) {
        try {
            if (jedis != null) {
                jedis.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
