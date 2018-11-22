package com.hz.learnboot.es.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * Elasticsearch 配置信息
 * elasticsearch spring-data 目前支持的最高版本为5.5 所以需要自己注入生成客户端
 *
 * Created by hezhao on 2018-07-20 18:09
 */
@Configuration
public class ElasticsearchConfig {
    private static final Logger LOG = LoggerFactory.getLogger(ElasticsearchConfig.class);

    @Value("${elasticsearch.host:127.0.0.1}")
    private String host;
    @Value("${elasticsearch.port:9200}")
    private int port;
    @Value("${elasticsearch.schema:http}")
    private String schema;

    private int connectTimeOut = 1000;
    private int socketTimeOut = 30000;
    private int connectionRequestTimeOut = 500;
    private int maxConnectNum = 100;
    private int maxConnectPerRoute = 100;

    private boolean uniqueConnectTimeConfig = true;
    private boolean uniqueConnectNumConfig = true;
    private RestClientBuilder builder;
    private RestHighLevelClient client;

    @Bean
    public RestHighLevelClient client() {
        try {
            builder = RestClient.builder(new HttpHost(host, port, schema));
            if (uniqueConnectTimeConfig) {
                setConnectTimeOutConfig();
            }
            if (uniqueConnectNumConfig) {
                setMutiConnectConfig();
            }
            client = new RestHighLevelClient(builder);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return client;
    }

    // 主要关于异步httpclient的连接延时配置
    public void setConnectTimeOutConfig() {
        builder.setRequestConfigCallback(requestConfigBuilder -> {
            requestConfigBuilder.setConnectTimeout(connectTimeOut);
            requestConfigBuilder.setSocketTimeout(socketTimeOut);
            requestConfigBuilder.setConnectionRequestTimeout(connectionRequestTimeOut);
            return requestConfigBuilder;
        });
    }

    // 主要关于异步httpclient的连接数配置
    public void setMutiConnectConfig() {
        builder.setHttpClientConfigCallback(httpClientBuilder -> {
            httpClientBuilder.setMaxConnTotal(maxConnectNum);
            httpClientBuilder.setMaxConnPerRoute(maxConnectPerRoute);
            return httpClientBuilder;
        });
    }

    // 关闭
    /*public void close() {
        if (client != null) {
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }*/
}