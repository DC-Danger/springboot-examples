package com.hz.learnboot.rocketmq;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * 消息消费者
 *
 * Created by hezhao on 2018-07-27 16:30
 */
public class Consumer {

    private final DefaultMQPushConsumer consumer;

    /**
     * 初始化消息消费者
     */
    public Consumer() {
        // 初始化一个consumer, consumerGroup名字作为构造方法的参数
        consumer = new DefaultMQPushConsumer("test-producer-group");

        // 设置NameServer地址，多个地址之间用;分隔
        consumer.setNamesrvAddr("127.0.0.1:9876");

        // 设置consumer的消费策略
        // CONSUME_FROM_LAST_OFFSET 默认策略，从该队列最尾开始消费，即跳过历史消息
        // CONSUME_FROM_FIRST_OFFSET 从队列最开始开始消费，即历史消息（还储存在broker的）全部消费一遍
        // CONSUME_FROM_TIMESTAMP 从某个时间点开始消费，和setConsumeTimestamp()配合使用，默认是半个小时以前
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
    }

    /**
     * 接收消息
     * @param topic 主题
     * @param tag 标记
     */
    public void getMessage(String topic, String tag){
        try {
            // 设置consumer所订阅的Topic和Tag，*代表全部的Tag
            consumer.subscribe(topic, tag);

            // 设置一个Listener，主要进行消息的逻辑处理
            consumer.registerMessageListener(new MessageListenerConcurrently() {

                @Override
                public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {

                    for (MessageExt msg : msgs) {
                        try {
                            String message = new String(msg.getBody(), RemotingHelper.DEFAULT_CHARSET);
                            System.out.println(Thread.currentThread().getName() + " Receive New Messages: " + message);
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }

                    // 返回消费状态
                    // CONSUME_SUCCESS 消费成功
                    // RECONSUME_LATER 消费失败，需要稍后重新消费
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                }
            });

            //调用start()方法启动consumer
            consumer.start();

            System.out.println("Consumer Started.");
        } catch (MQClientException e) {
            e.printStackTrace();
        }
    }

}
