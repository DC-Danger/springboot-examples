package com.hz.learnboot.cache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * Spring Boot 应用启动类
 *
 * Created by hezhao on 28/06/2018.
 */
@SpringBootApplication
@EnableCaching // 在 Spring Boot 中使用 @EnableCaching 开启缓存支持。
public class SpringBootCacheApplication {

	// Spring 定义 CacheManager 和 Cache 接口用来统一不同的缓存技术。例如 JCache、 Ehcache、 Hazelcast、 Guava、 Redis 等。
	// 在使用 Spring 集成 Cache 的时候，我们需要注册实现的 CacheManager 的 Bean。

	// Spring Boot 为我们自动配置了多个 CacheManager 的实现。
	// 包括 JcacheCacheConfiguration、 EhCacheCacheConfiguration、HazelcastCacheConfiguration、GuavaCacheConfiguration、RedisCacheConfiguration、SimpleCacheConfiguration 等。

	// Spring 从 Spring3.1 开始基于 java.util.concurrent.ConcurrentHashMap 实现的缓存管理器。
	// 所以，Spring Boot 默认使用 SimpleCacheConfiguration，即使用 ConcurrentMapCacheManager 作为缓存技术。
	// 但是要切换到其他缓存实现也很简单。

	public static void main(String[] args) {
		SpringApplication.run(SpringBootCacheApplication.class, args);
	}
}
