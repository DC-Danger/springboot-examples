package com.hz.learnboot.jpa.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import javax.sql.DataSource;

/** 自定义数据源配置类，声明两个数据源的Bean对象，以及使用application.yml配置文件内的前缀属性配置
 * @Author hezhao
 * @Time 2018-07-01 23:04
 */
@Configuration
public class DataSourceConfig {

    // 配置主数据源
    @Bean(name = "primaryDataSource")
    @Primary
    @ConfigurationProperties(prefix="spring.datasource.primary")
    public DataSource primaryDataSource() {
        // SpringBoot 默认使用的是org.apache.tomcat.jdbc.pool.DataSource数据源
        return DataSourceBuilder.create().build();
    }

    // 配置从数据源
    @Bean(name = "secondaryDataSource")
    @ConfigurationProperties(prefix="spring.datasource.secondary")
    public DataSource secondaryDataSource() {
        return DataSourceBuilder.create().build();
    }
}
