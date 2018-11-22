package com.hz.learnboot.mybatis.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

/** 从数据源配置信息
 * @Author hezhao
 * @Time 2018-07-01 0:03
 */
@Configuration
// 扫描 Mapper 接口并容器管理
@MapperScan(basePackages = ClusterDataSourceConfig.PACKAGE, sqlSessionFactoryRef = "clusterSqlSessionFactory")
public class ClusterDataSourceConfig {

    private static Logger logger = LoggerFactory.getLogger(ClusterDataSourceConfig.class);

    // 精确到 cluster 目录，以便跟其他数据源隔离
    static final String PACKAGE = "com.hz.learnboot.mybatis.dao.cluster";

    @Value("${spring.datasource.cluster.driver-class-name}")
    private String driverClassName;

    @Value("${spring.datasource.cluster.jdbc-url}")
    private String dbUrl;

    @Value("${spring.datasource.cluster.username}")
    private String username;

    @Value("${spring.datasource.cluster.password}")
    private String password;

    @Value("${spring.datasource.cluster.maxActive}")
    private int maxActive;

    @Value("${spring.datasource.cluster.initialSize}")
    private int initialSize;

    @Value("${spring.datasource.cluster.maxWait}")
    private int maxWait;

    @Value("${spring.datasource.cluster.minIdle}")
    private int minIdle;

    @Value("${spring.datasource.cluster.timeBetweenEvictionRunsMillis}")
    private int timeBetweenEvictionRunsMillis;

    @Value("${spring.datasource.cluster.minEvictableIdleTimeMillis}")
    private int minEvictableIdleTimeMillis;

    @Value("${spring.datasource.cluster.validationQuery}")
    private String validationQuery;

    @Value("${spring.datasource.cluster.testWhileIdle}")
    private boolean testWhileIdle;

    @Value("${spring.datasource.cluster.testOnBorrow}")
    private boolean testOnBorrow;

    @Value("${spring.datasource.cluster.testOnReturn}")
    private boolean testOnReturn;

    @Value("${spring.datasource.cluster.poolPreparedStatements}")
    private boolean poolPreparedStatements;

    @Value("${spring.datasource.cluster.maxPoolPreparedStatementPerConnectionSize}")
    private int maxPoolPreparedStatementPerConnectionSize;

    @Value("${spring.datasource.cluster.filters}")
    private String filters;

    @Bean(name = "clusterDataSource")
    @ConfigurationProperties(prefix="spring.datasource.cluster")
    public DataSource clusterDataSource() {
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(dbUrl);
        datasource.setUsername(username);
        datasource.setPassword(password);
        datasource.setDriverClassName(driverClassName);
        datasource.setInitialSize(initialSize);
        datasource.setMinIdle(minIdle);
        datasource.setMaxActive(maxActive);
        datasource.setMaxWait(maxWait);
        datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        datasource.setValidationQuery(validationQuery);
        datasource.setTestWhileIdle(testWhileIdle);
        datasource.setTestOnBorrow(testOnBorrow);
        datasource.setTestOnReturn(testOnReturn);
        datasource.setPoolPreparedStatements(poolPreparedStatements);
        datasource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
        try {
            datasource.setFilters(filters);
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("druid configuration initialization filter", e);
        }
        return datasource;
    }

    @Bean(name = "clusterTransactionManager")
    public DataSourceTransactionManager clusterTransactionManager(@Qualifier("clusterDataSource") DataSource clusterDataSource) {
        return new DataSourceTransactionManager(clusterDataSource);
    }

    @Autowired
    private PageInterceptor pageInterceptor;

    @Bean(name = "clusterSqlSessionFactory")
    public SqlSessionFactory clusterSqlSessionFactory(@Qualifier("clusterDataSource") DataSource clusterDataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(clusterDataSource);

        // 配置分页插件
        Interceptor[] interceptors = {pageInterceptor};
        sessionFactory.setPlugins(interceptors);

        return sessionFactory.getObject();
    }

}