package com.hz.learnboot.activemq;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 消息消费者
 *
 * Created by hezhao on 2018-07-24 15:01
 */
public class Consumer {

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

    private ThreadLocal<MessageConsumer> threadLocal = new ThreadLocal<>();

    public void init(){
        try {
            connectionFactory = new ActiveMQConnectionFactory(USERNAME,PASSWORD,BROKEN_URL);
            connection  = connectionFactory.createConnection();
            connection.start();
            // 自动确认消息
            session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public void getMessage(String queueName){
        try {
            // 创建一个消息队列
            Queue queue = session.createQueue(queueName);

            // 消息消费者
            MessageConsumer consumer;
            if(threadLocal.get() != null){
                consumer = threadLocal.get();
            }else{
                consumer = session.createConsumer(queue);
                threadLocal.set(consumer);
            }

            // 同步获取消息
//            while(true){
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                TextMessage msg = (TextMessage) consumer.receive();
//                if(msg != null) {
//                    msg.acknowledge(); // 告知已收到,jms服务器才会删除消息。
//                    String message = Thread.currentThread().getName()+": Consumer:我是消费者，我正在消费Msg - " + msg.getText() + "--->" + count.getAndIncrement();
//                    System.out.println(message);
//                }else {
//                    break;
//                }
//            }

            // 消息监听,异步获取消息
            consumer.setMessageListener(message -> {
                try {
                    if(null != message) {
                        TextMessage msg = (TextMessage) message;
                        if (msg != null) {
                            String str = Thread.currentThread().getName() + " - Consumer:我是消费者，我正在消费Msg - " + msg.getText() + "--->" + count.getAndIncrement();
                            System.out.println(str);
                        }
                    }
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            });

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

}
