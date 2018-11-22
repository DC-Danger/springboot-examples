package com.hz.learnboot.kafka.task;

import com.alibaba.fastjson.JSON;
import com.hz.learnboot.kafka.domain.Message;
import com.hz.learnboot.kafka.service.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 测试定时任务，往队列里写数据
 *
 * Created by hezhao on 2018-07-25 09:17
 */
@EnableScheduling
@Component
public class SenderTask {

    @Autowired
    private Sender sender;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");

    // 用于计数
    private AtomicInteger count = new AtomicInteger(0);

    // 发生文本消息
    @Scheduled(fixedRate = 2000)
    public void sendTextMessage() {
        int num = count.getAndIncrement();
        String message = "["+ num +"] " + Thread.currentThread().getName() + " - 我是生产者，我现在正在生产文本消息！现在的时间是：" + sdf.format(new Date());
        sender.sendMessage("test.string", message);
    }

    // 发生JSON消息
    @Scheduled(fixedRate = 3000)
    public void sendObjMessage() {
        int num = count.getAndIncrement();
        Message message = new Message();
        message.setId(System.currentTimeMillis());
        message.setMsg("[" + num + "]" + UUID.randomUUID().toString());
        message.setSendTime(new Date());
        sender.sendMessage("test.message", "obj", JSON.toJSONString(message));
    }

}
