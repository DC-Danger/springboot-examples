package com.hz.learnboot.mybatis.config;

import com.github.pagehelper.PageInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * 分页参数配置
 *
 * @Author hezhao
 * @Time 2018-07-08 10:45
 */
@Configuration
public class PageInterceptorConfig {

    @Bean
    public PageInterceptor pageInterceptor() {
        Properties properties = new Properties();
        properties.setProperty("helperDialect", "mysql");
        properties.setProperty("reasonable", "true");
        properties.setProperty("supportMethodsArguments", "true");
        properties.setProperty("offsetAsPageNum", "true");
        properties.setProperty("params", "pageNum=start;pageSize=limit;pageSizeZero=zero;reasonable=heli;count=contsql");
        PageInterceptor pageInterceptor = new PageInterceptor();
        pageInterceptor.setProperties(properties);
        return pageInterceptor;
    }

}
