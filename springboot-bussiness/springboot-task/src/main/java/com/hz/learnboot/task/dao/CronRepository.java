package com.hz.learnboot.task.dao;

import com.hz.learnboot.task.domain.Cron;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * Cron表达式保存在数据库
 *
 * Created by hezhao on 2018-07-11 20:40
 */
public interface CronRepository extends JpaRepository<Cron, Long> {

    Cron findByKey(String key);

    @Modifying
    @Transactional(timeout = 10)
    @Query("update Cron c set c.cron=:cron where c.key=:key")
    void updateCronByKey(@Param("key") String key, @Param("cron") String cron);

}

