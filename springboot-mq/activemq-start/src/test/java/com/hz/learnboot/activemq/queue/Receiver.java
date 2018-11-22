package com.hz.learnboot.activemq.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 队列消息的接收
 *
 * Queue支持存在多个消费者，但是对一个消息而言，只会有一个消费者可以消费。
 *
 * @Author hezhao
 * @Time 2018-07-24 23:55
 */
public class Receiver {

    public static void main(String[] args) {
        receive();
    }

    // 消息队列接收
    public static void receive(){
        Connection connection;
        Session session;
        Destination destination;
        MessageConsumer consumer;

        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("admin","admin","failover:tcp://127.0.0.1:61616");

        // 如果传输的消息为ObjectMessage, 需要调用setTrustedPackages()方法添加信任的包，或者调用setTrustAllPackage()方法信任所有
        connectionFactory.setTrustAllPackages(true);

        try {
            // 构造从工厂得到连接对象
            connection = connectionFactory.createConnection();

            // 启动
            connection.start();

            // 获取操作连接
            // 这个最好还是有事务
            session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
            destination = session.createQueue("jelynn-queue");
            consumer = session.createConsumer(destination);

            // 消息监听
            consumer.setMessageListener(new MessageListener() {
                @Override
                public void onMessage(Message message) {
                    try {
                        // 对象消息
                        if(null != message){
                            MqBean mqBean = (MqBean) ((ObjectMessage)message).getObject();
                            System.out.println("接收到消息:" + mqBean);
                        }

                        // 文本消息
//                        if(null != message){
//                           String str = ((TextMessage)message).getText();
//                           System.out.println("接收到消息  : "+str);
//                        }
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            });

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

}
