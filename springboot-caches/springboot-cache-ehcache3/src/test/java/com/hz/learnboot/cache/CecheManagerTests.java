package com.hz.learnboot.cache;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.PersistentCacheManager;
import org.ehcache.UserManagedCache;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.builders.UserManagedCacheBuilder;
import org.ehcache.config.units.EntryUnit;
import org.ehcache.config.units.MemoryUnit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;

/**
 * Test CacheManager
 *
 * Created by hezhao on 2018-07-05 11:09
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CecheManagerTests {

    // 通用的读写使用CacheManager,兼容3.0和2.0版
    @Test
    public void test1() {
        CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder()
                .withCache("preConfigured",
                        CacheConfigurationBuilder.newCacheConfigurationBuilder(Long.class, String.class,
                                ResourcePoolsBuilder.heap(100))
                                .build())
                .build(true);

        Cache<Long, String> preConfigured = cacheManager.getCache("preConfigured", Long.class, String.class);

        Cache<Long, String> myCache = cacheManager.createCache("myCache",
                CacheConfigurationBuilder.newCacheConfigurationBuilder(Long.class, String.class,
                        ResourcePoolsBuilder.heap(100)).build());

        for (long i=0; i<=5; i++){
            //写
            myCache.put(i, "@" + i);
            //读
            String value = myCache.get(i);
            System.out.println("get at " + i + ":" + value);
        }

        // cacheManager.removeCache("preConfigured");
        cacheManager.close();
    }

    // 3.0的读写新泛型方法UserManagedCache,更加简洁
    @Test
    public void test2() {
        UserManagedCache<Integer, String> userManagedCache =
                UserManagedCacheBuilder.newUserManagedCacheBuilder(Integer.class, String.class)
                        .build(false);
        userManagedCache.init();

        for (int i = 0; i <= 5; i++) {
            //写
            userManagedCache.put(i, "#" + i);
            //读
            String value = userManagedCache.get(i);
            System.out.println("get at " + i + ":" + value);
        }

        userManagedCache.close();
    }

    // 三层缓存策略
    // 缓存数据有三级：内存、堆外缓存Off-Heap、Disk缓存，因此无需担心容量问题。还可以通过RMI、可插入API等方式进行分布式缓存。
    // Ehcache2.x版本是2级缓存，内存和磁盘，新版增加了更灵活的一级，堆外缓存（off-heap），这既是独立的进程缓存，还是JVM堆外的系统缓存
    // 建议：
    // - 常被查询、最重要、数据量较小的数据存放在堆缓存，不用担心JVM的重启，有持久化机制；
    // - 常被查询、数据量中等的数据存放在堆外缓存，几个G就好了，不用担心服务器的重启，有持久化机制；
    // - 不常用、大量的数据、但又不想占用数据库IO的数据，放在Disk缓存，容量自便；
    @Test
    public void test3() {
        PersistentCacheManager persistentCacheManager = CacheManagerBuilder.newCacheManagerBuilder()
                .with(CacheManagerBuilder.persistence(getStoragePath() + File.separator + "/cacheData"))
                .withCache("threeTieredCache",
                        CacheConfigurationBuilder.newCacheConfigurationBuilder(Integer.class, String.class,
                                ResourcePoolsBuilder.newResourcePoolsBuilder()
                                        .heap(10, EntryUnit.ENTRIES)  // 堆
                                        .offheap(1, MemoryUnit.MB)    // 堆外
                                        .disk(20, MemoryUnit.GB)      // 磁盘
                        )
                ).build(true);

        Cache<Integer, String> threeTieredCache = persistentCacheManager.getCache("threeTieredCache", Integer.class, String.class);

        //写
        for (int i=0; i<=20000; i++){
            threeTieredCache.put(i, "$" + i);
        }

        //读取
        for (int i=0; i<=20000; i++){
            String value = threeTieredCache.get(i);
            System.out.println("get at " + i + ":" + value);
        }

        persistentCacheManager.close();

        // 测试：数据量大了之后会写入磁盘
    }

    // 数据保存的盘符
    private static String getStoragePath() {
        return "D:";
    }

}
