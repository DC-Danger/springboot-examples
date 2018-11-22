package com.hz.learnboot.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;

/**
 * 消息消费者
 *
 * Created by hezhao on 2018-07-27 16:30
 */
public class Receiver {

    private final KafkaConsumer<String, String> kafkaConsumer;

    /**
     * 初始化消息消费者
     *
     * @param topics 主题,可多个
     */
    public Receiver(String... topics) {
        Properties props = new Properties();
        // 指定了要连接到的broker,多个逗号分隔
        props.put("bootstrap.servers", "127.0.0.1:9092");
        // 分组ID
        props.put("group.id", "group-1");
        // 自动提交
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("auto.offset.reset", "earliest");
        props.put("session.timeout.ms", "30000");
        // 用来指定key的反序列化器
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        // 用来指定value的反序列化器
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        // 初始化消费者
        kafkaConsumer = new KafkaConsumer<>(props);
        // 订阅topic消息
        kafkaConsumer.subscribe(Arrays.asList(topics));
    }

    /**
     * 接收消息
     */
    public void getMessage(){
        while(true){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 调用poll方法来轮循Kafka集群的消息，其中的参数100是超时时间
            ConsumerRecords<String, String> records = kafkaConsumer.poll(100);
            for (ConsumerRecord<String, String> record : records) {
                String message = record.value();
                String str = Thread.currentThread().getName() + " - 已消费 - topic = " + record.topic()
                        + ", offset = " + record.offset() + ", message = " + message;
                System.out.println(str);
            }
        }
    }

}
