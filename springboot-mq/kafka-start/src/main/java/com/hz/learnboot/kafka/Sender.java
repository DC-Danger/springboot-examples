package com.hz.learnboot.kafka;

import com.alibaba.fastjson.JSON;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

/**
 * 消息生产者
 *
 * Created by hezhao on 2018-07-27 16:30
 */
public class Sender {

    private final Producer<String, String> producer;
    private final String topic;

    /**
     * 初始化消息生产者
     *
     * @param topic 主题
     */
    public Sender(String topic) {
        Properties props = new Properties();
        // 指定了要连接到的broker,多个逗号分隔
        props.put("bootstrap.servers", "127.0.0.1:9092");
        // 判别请求是否为完整的条件,指定了“all”将会阻塞消息，这种设置性能最低，但是是最可靠的。
        props.put("acks", "all");
        // 重试次数，默认0次，如果启用重试，则会有重复消息的可能性。
        props.put("retries", 0);
        // 缓存的大小,默认16k
        props.put("batch.size", 0);
        // 默认缓冲可立即发送，即遍缓冲空间还没有满，但是，如果你想减少请求的数量，可以设置linger.ms大于0。这将指示生产者发送请求之前等待一段时间，希望更多的消息填补到未满的批中。
        props.put("linger.ms", 1);
        // 控制生产者可用的缓存总量，单位字节，默认32M，如果消息发送速度比其传输到服务器的快，将会耗尽这个缓存空间。当缓存空间耗尽，其他发送调用将被阻塞。
        props.put("buffer.memory", 32 * 1024 * 1024);
        // 阻塞时间,超出将抛出一个TimeoutException。单位毫秒
        props.put("max.block.ms", 30 * 1000);
        // 用来指定key的序列化器
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        // 用来指定value的序列化器
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        // 初始化生产者
        producer = new KafkaProducer<>(props);
        this.topic = topic;
    }

    /**
     * 发送消息
     *
     * @param message 消息
     */
    public void sendMessage(String message){
//        ProducerRecord<String,String> record = new ProducerRecord<>(topic, key, message);
        ProducerRecord<String,String> record = new ProducerRecord<>(topic, message);
        try {
            producer.send(record).get();
            System.out.println("已发送 - " + message);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

}
