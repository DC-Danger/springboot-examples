package com.hz.learnboot.activemq.topic;

import org.apache.activemq.ActiveMQConnectionFactory;
import javax.jms.*;

/**
 * 订阅消息的接收
 * 可以定义多个Subscriber，进行订阅消息的接收,每个Subscriber都能接收到订阅消息
 *
 * <p/>
 * Number Of Pending Messages 等待消费的消息这个是当前未出队列的数量。可以理解为总接收数-总出队列数
 * Messages Enqueued 进入队列的消息进入队列的总数量,包括出队列的。这个数量只增不减
 * Messages Dequeued 出了队列的消息可以理解为是消费这消费掉的数量
 *
 * @Author hezhao
 * @Time 2018-07-25 0:34
 */
public class Subscriber {

    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("failover:tcp://127.0.0.1:61616");
        Connection connection = factory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic("myTopic.messages");

        MessageConsumer consumer = session.createConsumer(topic);
        consumer.setMessageListener(new MessageListener() {
            public void onMessage(Message message) {
                TextMessage tm = (TextMessage) message;
                try {
                    System.out.println("Received message: " + tm.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
//        session.close();
//        connection.stop();
//        connection.close();
    }

}
