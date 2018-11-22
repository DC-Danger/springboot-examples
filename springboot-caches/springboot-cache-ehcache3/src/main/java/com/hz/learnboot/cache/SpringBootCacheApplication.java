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

	// Ehcache3主要的特点：
	// - 缓存数据有三级(Ehcache2.x是2级缓存，内存和磁盘)：内存、堆外缓存Off-Heap、Disk缓存，因此无需担心容量问题。还可以通过RMI、可插入API等方式进行分布式缓存。
	// - 缓存数据会在虚拟机重启的过程中写入磁盘，持久化。
	// - 具有缓存和缓存管理器的侦听接口。
	// - 支持多缓存管理器实例，以及一个实例的多个缓存区域。

	public static void main(String[] args) {
		SpringApplication.run(SpringBootCacheApplication.class, args);
	}
}
