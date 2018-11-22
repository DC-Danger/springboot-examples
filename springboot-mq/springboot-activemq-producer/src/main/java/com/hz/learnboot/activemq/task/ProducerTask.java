package com.hz.learnboot.activemq.task;

import com.hz.learnboot.activemq.domain.Person;
import com.hz.learnboot.activemq.service.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.Queue;
import javax.jms.Topic;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 测试定时任务，往队列里写数据
 *
 * Created by hezhao on 2018-07-25 09:17
 */
@EnableScheduling
@Component
public class ProducerTask {

    @Autowired
    private Producer producer;

    // Queue队列
    @Resource(name = "sampleQueue")
    private Queue queue;

    // Topic队列
    @Resource(name = "sampleTopic")
    private Topic topic;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
    private static final String[] names = new String[]{"张三", "李四", "王二麻子", "赵一", "钱一", "孙一", "诸葛孔明", "刘备", "张飞", "曹操"};

    // 用于计数
    private AtomicInteger count = new AtomicInteger(0);

    // 2秒一次
    @Scheduled(fixedRate = 2000)
    public void sendQueueMsg1() {
        int num = count.getAndIncrement();
        int forMaxIndex = new Random().nextInt(50);
        Person person = Person.builder().name(names[forMaxIndex % 10] + "[" + num + "]").age(forMaxIndex).build();
        producer.sendMessage(queue, person);
    }
    // 测试多个发送者
    @Scheduled(fixedRate = 2000)
    public void sendQueueMsg2() {
        int num = count.getAndIncrement();
        int forMaxIndex = new Random().nextInt(50);
        Person person = Person.builder().name(names[forMaxIndex % 10] + "[" + num + "]").age(forMaxIndex).build();
        producer.sendMessage(queue, person);
    }


    // 3秒一次
    @Scheduled(fixedRate = 3000)
    public void sendTopicMsg1() {
        int num = count.getAndIncrement();
        String message = "["+ num +"] " + Thread.currentThread().getName() + " - 我是生产者1，我现在正在生产Topic消息！现在的时间是：" + sdf.format(new Date());
        producer.sendMessage(topic, message);
    }
    // 测试多个发送者
    @Scheduled(fixedRate = 3000)
    public void sendTopicMsg2() {
        int num = count.getAndIncrement();
        String message = "["+ num +"] " + Thread.currentThread().getName() + " - 我是生产者2，我现在正在生产Topic消息！现在的时间是：" + sdf.format(new Date());
        producer.sendMessage(topic, message);
    }

}
