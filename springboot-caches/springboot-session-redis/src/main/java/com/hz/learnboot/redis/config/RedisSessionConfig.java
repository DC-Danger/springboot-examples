package com.hz.learnboot.redis.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * Session配置
 *
 * Created by hezhao on 2018-07-06 18:12
 */
@Configuration
// maxInactiveIntervalInSeconds设置Session失效时间,默认是1800秒过期，这里测试修改为60秒
// 使用Redis Session之后，原Boot的server.servlet.session.timeout属性不再生效
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 60)
public class RedisSessionConfig {

}
