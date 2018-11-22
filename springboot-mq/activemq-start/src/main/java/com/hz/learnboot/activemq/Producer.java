package com.hz.learnboot.activemq;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 消息生产者
 *
 * Created by hezhao on 2018-07-24 15:01
 */
public class Producer {

    // ActiveMq 的默认用户名
    private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
    // ActiveMq 的默认登录密码
    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
    // ActiveMQ 的链接地址
    private static final String BROKEN_URL = ActiveMQConnection.DEFAULT_BROKER_URL;

    // 用于计数
    private AtomicInteger count = new AtomicInteger(0);

    // 链接工厂
    private ConnectionFactory connectionFactory;
    // 链接对象
    private Connection connection;
    // 事务管理
    private Session session;

    private ThreadLocal<MessageProducer> threadLocal = new ThreadLocal<>();

    public void init(){
        try {
            // 创建一个链接工厂
            connectionFactory = new ActiveMQConnectionFactory(USERNAME,PASSWORD,BROKEN_URL);
            // 从工厂中创建一个链接
            connection  = connectionFactory.createConnection();
            // 开启链接
            connection.start();
            // 创建一个事务（这里通过参数可以设置事务的级别）
            session = connection.createSession(true,Session.SESSION_TRANSACTED);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String queueName){
        try {
            // 创建一个消息队列
            Queue queue = session.createQueue(queueName);

            // 消息生产者
            MessageProducer messageProducer;
            if(threadLocal.get() != null){
                messageProducer = threadLocal.get();
            }else{
                messageProducer = session.createProducer(queue);
                threadLocal.set(messageProducer);
            }

            int num = count.getAndIncrement();
            // 创建一条文本消息
            String message = Thread.currentThread().getName() + " - Producer:我是生产者，我现在正在生产东西！,count:" + num;
            TextMessage msg = session.createTextMessage(message);
            System.out.println(message);
            // 发送消息
            messageProducer.send(msg);
            // 提交事务
            session.commit();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

}
