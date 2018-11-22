package com.hz.learnboot.mongodb.config;

import com.mongodb.MongoClientURI;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * MongoDB 第二数据源配置
 *
 * Created by hezhao on 2018-07-05 19:02
 */
@Configuration
@EnableMongoRepositories(basePackages = "com.hz.learnboot.mongodb.repository.secondary", mongoTemplateRef = SecondaryMongoConfig.MONGO_TEMPLATE)
public class SecondaryMongoConfig {
    protected static final String MONGO_TEMPLATE = "secondaryMongoTemplate";

    @Value("${spring.data.mongodb.secondary.uri}")
    private String uri;

    @Bean(name = "secondaryMongoClientURI")
    public MongoClientURI secondaryMongoClientURI() {
        return new MongoClientURI(uri);
    }

    @Bean(name = MONGO_TEMPLATE)
    public MongoTemplate secondaryMongoTemplate() {
        return new MongoTemplate(secondaryFactory(secondaryMongoClientURI()));
    }

    @Bean
    public MongoDbFactory secondaryFactory(@Qualifier("secondaryMongoClientURI") MongoClientURI mongoClientURI) {
        return new SimpleMongoDbFactory(mongoClientURI);
    }
}
