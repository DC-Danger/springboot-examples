package com.hz.learnboot.banner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot 应用启动类
 *
 * 改变启动图标只需要在resources目录下放一个banner.*文件即可。
 *
 * Created by hezhao on 28/06/2018.
 */
@SpringBootApplication
public class SpringBootBannerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootBannerApplication.class, args);
	}
}
