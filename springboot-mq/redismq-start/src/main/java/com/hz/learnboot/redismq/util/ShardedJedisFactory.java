package com.hz.learnboot.redismq.util;

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import java.util.ArrayList;
import java.util.List;

/**
 * Jedis工厂类, 切片(分布式集群)
 *
 * Created by hezhao on 2018-07-24 17:00
 */
public class ShardedJedisFactory {

    // Redis服务器IP数组
    private static String[] HOST = {
            "127.0.0.1"
    };

    // Redis的端口号数组
    private static int[] PORT = {
            6379
    };

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

    // 切片连接池
    private static ShardedJedisPool shardedJedisPool = null;

    /**
     * 初始化Redis连接池，切片
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

            // slave链接
            List<JedisShardInfo> shards = new ArrayList<>();

            // 可添加多个链接
            for (int i = 0; i < HOST.length; i++) {
                shards.add(new JedisShardInfo(HOST[i], PORT[i], TIMEOUT));
            }

            // 构造池
            shardedJedisPool = new ShardedJedisPool(config, shards);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取Jedis实例，切片
     *
     * @return
     */
    public synchronized static ShardedJedis getShardedJedis() {
        try {
            if (shardedJedisPool != null) {
                ShardedJedis resource = shardedJedisPool.getResource();
                return resource;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 关闭连接池，切片
     */
    public static void close() {
        shardedJedisPool.close();
    }

    /**
     * 关闭Jedis切片客户端
     * @param shardedJedis
     */
    public void close(ShardedJedis shardedJedis) {
        try {
            if (shardedJedis != null) {
                shardedJedis.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
