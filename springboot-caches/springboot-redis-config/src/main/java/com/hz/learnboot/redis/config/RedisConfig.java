package com.hz.learnboot.redis.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

import java.time.Duration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 手动创建Redis配置信息
 *
 * Created by hezhao on 2018-07-05 08:53
 */
@Configuration
@PropertySource("classpath:application.yml")
@EnableCaching // @EnableCaching注解是spring framework中的注解驱动的缓存管理功能。如果你使用了这个注解，那么你就不需要在XML文件中配置cache manager了。
public class RedisConfig {
    @Autowired
    private Environment env;

    // 构建Jedis链接
    @Bean(name = "jedisConnectionFactory")
    public JedisConnectionFactory jedisConnectionFactory() {
        // 单机版
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration ();
        redisStandaloneConfiguration.setHostName(env.getProperty("redis.standalone.host").trim());
        redisStandaloneConfiguration.setPort(Integer.parseInt(env.getProperty("redis.standalone.port").trim()));
        redisStandaloneConfiguration.setDatabase(Integer.parseInt(env.getProperty("redis.standalone.database").trim()));
        redisStandaloneConfiguration.setPassword(RedisPassword.of(env.getProperty("redis.standalone.password").trim()));

        JedisClientConfiguration.JedisClientConfigurationBuilder jedisClientConfiguration = JedisClientConfiguration.builder();
        jedisClientConfiguration.connectTimeout(Duration.ofMillis(Long.parseLong(env.getProperty("redis.standalone.timeout").trim())));//  connection timeout

        JedisConnectionFactory factory = new JedisConnectionFactory(redisStandaloneConfiguration, jedisClientConfiguration.build());
        return factory;

//        // 集群版
//        Map<String, Object> map = new HashMap<>();
//        map.put("spring.redis.cluster.nodes", env.getProperty("redis.cluster.nodes").trim());
//        map.put("spring.redis.cluster.max-redirects", env.getProperty("redis.cluster.max-redirects").trim());
//        MapPropertySource mapPropertySource = new MapPropertySource("redisClusterConfiguration", map);
//        RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration(mapPropertySource);
//
//        // 连接池配置
//        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
//        jedisPoolConfig.setMaxIdle(Integer.parseInt(env.getProperty("redis.pool.maxIdle").trim()));
//        jedisPoolConfig.setMinIdle(Integer.parseInt(env.getProperty("redis.pool.minIdle").trim()));
//        jedisPoolConfig.setMaxWaitMillis(Integer.parseInt(env.getProperty("redis.pool.maxWait").trim()));
//        jedisPoolConfig.setMaxTotal(Integer.parseInt(env.getProperty("redis.pool.maxTotal").trim()));
//        jedisPoolConfig.setTestOnBorrow(Boolean.valueOf(env.getProperty("redis.pool.testOnBorrow").trim()));
//        jedisPoolConfig.setTestOnReturn(Boolean.valueOf(env.getProperty("redis.pool.testOnReturn").trim()));
//
//        JedisConnectionFactory factory = new JedisConnectionFactory(redisClusterConfiguration, jedisPoolConfig);
//        return factory;
    }

    // RedisTemplate
    @Bean(name = "redisTemplate")
    public RedisTemplate redisTemplate(@Qualifier("jedisConnectionFactory") RedisConnectionFactory factory) {
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(factory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    // StringRedisTemplate
    @Bean(name = "stringRedisTemplate")
    public StringRedisTemplate stringRedisTemplate(@Qualifier("jedisConnectionFactory") RedisConnectionFactory factory) {
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
        stringRedisTemplate.setConnectionFactory(factory);
        stringRedisTemplate.setKeySerializer(new StringRedisSerializer());
        stringRedisTemplate.afterPropertiesSet();
        return stringRedisTemplate;
    }

    // 配置CacheManager
    @Bean
    public CacheManager cacheManager(@Qualifier("jedisConnectionFactory") RedisConnectionFactory factory) {
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();  // 生成一个默认配置，通过config对象即可对缓存进行自定义配置
        config = config.entryTtl(Duration.ofMinutes(1))     // 设置缓存的默认过期时间，也是使用Duration设置
                .disableCachingNullValues();     // 不缓存空值

        // 设置一个初始化的缓存空间set集合
        Set<String> cacheNames =  new HashSet<>();
        cacheNames.add("my-redis-cache1");
        cacheNames.add("my-redis-cache2");

        // 对每个缓存空间应用不同的配置
        Map<String, RedisCacheConfiguration> configMap = new HashMap<>();
        configMap.put("my-redis-cache1", config);
        configMap.put("my-redis-cache2", config.entryTtl(Duration.ofSeconds(120)));

        RedisCacheManager cacheManager = RedisCacheManager.builder(factory)     // 使用自定义的缓存配置初始化一个cacheManager
                .initialCacheNames(cacheNames)  // 注意这两句的调用顺序，一定要先调用该方法设置初始化的缓存名，再初始化相关的配置
                .withInitialCacheConfigurations(configMap)
                .build();
        return cacheManager;
    }

}
