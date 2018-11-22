package com.hz.learnboot.rabbitmq.util;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * RabbitMQ 链接工具类
 *
 * Created by hezhao on 2018-07-26 17:37
 */
public class ConnectionUtil {

    /** 主机 */
    private static final String host = "localhost";
    /** 用户名 */
    private static final String username = "guest";
    /** 密码 */
    private static final String password = "guest";
    /** 端口号 */
    private static final Integer port = 5672;
    /** 虚拟主机，默认为 "/" */
    private static final String virtualHost = "/";

    private static final ConnectionFactory connectionFactory;

    static {
        // 创建连接工厂
        connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(host);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setPort(port);
        connectionFactory.setVirtualHost(virtualHost);
    }

    /**
     * 获取一个新的连接
     * @return
     * @throws IOException
     * @throws TimeoutException
     */
    public static Connection getConnection() throws IOException, TimeoutException {
        return connectionFactory.newConnection();
    }

    /**
     * 关闭连接
     * @return
     * @throws IOException
     * @throws TimeoutException
     */
    public static void close(Connection connection) throws IOException {
        connection.close();
    }

}
