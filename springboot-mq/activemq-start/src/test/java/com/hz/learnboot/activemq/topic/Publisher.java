package com.hz.learnboot.activemq.topic;

import org.apache.activemq.ActiveMQConnectionFactory;
import javax.jms.*;

/**
 * 订阅消息的发送
 *
 * @Author hezhao
 * @Time 2018-07-25 0:32
 */
public class Publisher {

    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("failover:tcp://127.0.0.1:61616");
        Connection connection = factory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic("myTopic.messages");

        MessageProducer producer = session.createProducer(topic);
        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

        while(true) {
            TextMessage message = session.createTextMessage();
            message.setText("message_" + System.currentTimeMillis());
            producer.send(message);
            System.out.println("Sent message: " + message.getText());

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
//        session.close();
//        connection.stop();
//        connection.close();
    }

}
