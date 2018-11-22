package com.hz.learnboot.mongodb.config;

import com.mongodb.MongoClientURI;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * MongoDB 主数据源配置
 *
 * Created by hezhao on 2018-07-05 19:02
 */
@Configuration
@EnableMongoRepositories(basePackages = "com.hz.learnboot.mongodb.repository.primary", mongoTemplateRef = PrimaryMongoConfig.MONGO_TEMPLATE)
public class PrimaryMongoConfig {
    protected static final String MONGO_TEMPLATE = "primaryMongoTemplate";

    @Value("${spring.data.mongodb.primary.uri}")
    private String uri;

    @Bean(name = "primaryMongoClientURI")
    public MongoClientURI primaryMongoClientURI() {
        return new MongoClientURI(uri);
    }

    @Primary
    @Bean(name = MONGO_TEMPLATE)
    public MongoTemplate primaryMongoTemplate() {
        return new MongoTemplate(primaryFactory(primaryMongoClientURI()));
    }

    @Bean
    @Primary
    public MongoDbFactory primaryFactory(@Qualifier("primaryMongoClientURI") MongoClientURI mongoClientURI) {
        return new SimpleMongoDbFactory(mongoClientURI);
    }

}
