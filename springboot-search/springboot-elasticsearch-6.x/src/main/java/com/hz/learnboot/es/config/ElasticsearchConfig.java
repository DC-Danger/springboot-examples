package com.hz.learnboot.es.config;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;

/**
 *
 * Elasticsearch 配置信息
 *
 * Created by hezhao on 2018-07-20 18:09
 */
@Configuration
public class ElasticsearchConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(ElasticsearchConfig.class);

    /** 集群地址 */
    @Value("${elasticsearch.host}")
    private String host;

    /** 端口 */
    @Value("${elasticsearch.port}")
    private String port;

    /** 集群名称 */
    @Value("${elasticsearch.cluster.name}")
    private String clusterName;

    /** 连接池大小 */
    @Value("${elasticsearch.pool.size}")
    private String poolSize;

    /**
     * TransportClient管理类
     *
     * @return
     */
    @Bean(name = "transportClient")
    public TransportClient transportClient() {
        LOGGER.info("Elasticsearch初始化开始。。。。。");
        TransportClient transportClient = null;
        try {
            // 配置信息
            Settings esSetting = Settings.builder()
                    .put("cluster.name", clusterName) // 集群名字
                    .put("client.transport.sniff", true) // 增加嗅探机制，找到ES集群
                    .put("thread_pool.search.size", Integer.parseInt(poolSize)) // 增加线程池个数，暂时设为10
                    .build();
            // 配置信息Settings自定义
            transportClient = new PreBuiltTransportClient(esSetting);

            // 可添加多个地址
            TransportAddress transportAddress = new TransportAddress(InetAddress.getByName(host), Integer.valueOf(port));
            transportClient.addTransportAddresses(transportAddress);
        } catch (Exception e) {
            LOGGER.error("elasticsearch TransportClient create error!!", e);
        }
        return transportClient;
    }

}
