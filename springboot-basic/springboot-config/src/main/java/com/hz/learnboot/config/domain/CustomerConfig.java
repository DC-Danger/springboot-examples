package com.hz.learnboot.config.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @Author hezhao
 * @Time 2018-07-07 13:37
 */
@Configuration
@ConfigurationProperties
@PropertySource("classpath:config/customer.properties")
@Component
@Data @NoArgsConstructor
public class CustomerConfig {
    private Map<String, String> info;

    private List<String> blogs;
}
