package com.hz.learnboot.task;

import com.hz.learnboot.task.dao.CronRepository;
import com.hz.learnboot.task.domain.Cron;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableScheduling // 开启定时任务，发现注解了@Scheduled的任务并后台执行
public class Application {

	private static Logger logger = LoggerFactory.getLogger(Application.class);

	@Autowired
	private CronRepository cronRepository;

	// 初始化cron数据
	@PostConstruct
	public void initialCron() {
		cronRepository.save(Cron.builder().key("default").cron("0/2 * * * * ?").build());
		logger.info("初始化Cron数据完成。");
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		logger.info("========================================开始运行定时任务=====================================\n");
	}
}

