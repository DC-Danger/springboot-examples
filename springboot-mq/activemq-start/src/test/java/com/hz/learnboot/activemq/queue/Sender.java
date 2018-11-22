package com.hz.learnboot.activemq.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 队列消息的发送
 *
 * @Author hezhao
 * @Time 2018-07-24 23:53
 */
public class Sender {

    public static void main(String[] args) {
        send();
    }

    // 队列消息的发送
    public static void send() {
        Connection connection;
        Session session;
        Destination destination;
        MessageProducer producer;
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("admin", "admin", "failover:tcp://127.0.0.1:61616");

        // 如果传输的消息为ObjectMessage, 需要调用setTrustedPackages()方法添加信任的包，或者调用setTrustAllPackage()方法信任所有
        connectionFactory.setTrustAllPackages(true);

        try {
            connection = connectionFactory.createConnection();
            connection.start();

            // 第一个参数是是否是事务型消息，设置为true,第二个参数无效
            // 第二个参数是
            // Session.AUTO_ACKNOWLEDGE         自动确认，客户端发送和接收消息不需要做额外的工作。异常也会确认消息，应该是在执行之前确认的
            // Session.CLIENT_ACKNOWLEDGE       客户端确认。客户端接收到消息后，必须调用javax.jms.Message的acknowledge方法。jms服务器才会删除消息。可以在失败的时候不确认消息,不确认的话不会移出队列，一直存在，下次启动继续接受。接收消息的连接不断开，其他的消费者也不会接受（正常情况下队列模式不存在其他消费者）
            // Session.SESSION_TRANSACTED       事务提交并确认。当session使用事务时，就是使用此模式。在事务开启之后，和session.commit()之前，所有消费的消息，要么全部正常确认，要么全部redelivery。这种严谨性，通常在基于GROUP(消息分组)或者其他场景下特别适合。
            // Session.DUPS_OK_ACKNOWLEDGE      允许副本的确认模式。一旦接收方应用程序的方法调用从处理消息处返回，会话对象就会确认消息的接收；而且允许重复确认。在需要考虑资源使用时，这种模式非常有效。
            session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);

            destination = session.createQueue("jelynn-queue");  // 点对点模型
            producer = session.createProducer(destination);
            // NON_PERSISTENT
            // PERSISTENT
            producer.setDeliveryMode(DeliveryMode.PERSISTENT); //消息持久化，默认就是持久的（未消费的消息会持久化）
            // ObjectMessage
            MqBean mqBean = new MqBean();
            mqBean.setAge(20);
            int i = 0;
            String str;
            while (true) {
                i++;

                // 文本消息
//                str = "小黄" + i;
//                producer.send(session.createTextMessage(str));

                // 对象消息
                mqBean.setName("小黄" + i);
                producer.send(session.createObjectMessage(mqBean));

                System.out.println("正在生产:" + mqBean);
                Thread.sleep(1000);
            }
//            producer.close();

        } catch (JMSException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
