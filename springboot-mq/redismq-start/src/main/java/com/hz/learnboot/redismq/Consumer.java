package com.hz.learnboot.redismq;

import com.hz.learnboot.redismq.util.JedisFactory;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * 消息消费者
 *
 * Created by hezhao on 2018-07-24 15:01
 */
public class Consumer {

    private String customerName;
    private volatile int count;
    private Jedis jedis;

    public Consumer(String name) {
        this.customerName = name;
    }

    public void init() {
        jedis = JedisFactory.getJedis();
    }

    // 这样做有一个问题存在，即需要不停的调用rpop方法查看List中是否有待处理消息。每调用一次都会发起一次连接，这会造成不必要的浪费。
    /*public void getMessage() {
        String message = jedis.rpop(Producer.MESSAGE_KEY);
        if(message != null) {
            count++;
            handle(message);
        }
    }*/

    // 可以使用brpop指令，这个指令只有在有元素时才返回，没有则会阻塞直到超时返回null
    public void getMessage() {
        /*
         * brpop支持多个列表(队列)
         * brpop指令是支持队列优先级的，比如这个例子中MESSAGE_KEY的优先级大于testKey（顺序决定）。
         * 如果两个列表中都有元素，会优先返回优先级高的列表中的元素，所以这儿优先返回MESSAGE_KEY
         * 0表示不限制等待，会一直阻塞在这儿
         */
        List<String> messages = jedis.brpop(0, Producer.MESSAGE_KEY, "testKey");
        if(messages.size() != 0) {
            // 由于该指令可以监听多个Key,所以返回的是一个列表
            // 列表由2项组成，1) 列表名，2)数据
            String keyName = messages.get(0);
            // 如果返回的是MESSAGE_KEY的消息
            if(Producer.MESSAGE_KEY.equals(keyName)) {
                String message = messages.get(1);
                if(message != null) {
                    count++;
                    handle(message);
                }
            }
        }
        System.out.println("=======================");
    }



    public void handle(String message) {
        System.out.println(customerName + " 正在处理消息,消息内容是: " + message + " 这是第" + count + "条");
    }

}
