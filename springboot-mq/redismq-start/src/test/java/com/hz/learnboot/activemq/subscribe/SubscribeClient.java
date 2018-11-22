package com.hz.learnboot.activemq.subscribe;

import com.hz.learnboot.redismq.util.JedisFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

/**
 * 发布/订阅模式
 * 消息订阅方客户端
 *
 * Created by hezhao on 2018-07-24 18:30
 */
public class SubscribeClient {

    private Jedis jedis;
    private static final String EXIT_COMMAND = "exit";

    public SubscribeClient() {
        jedis = JedisFactory.getJedis();
    }

    public void subscribe(String ...channel) {
        if(channel == null || channel.length <= 0) {
            return;
        }
        // 消息处理,接收到消息时如何处理
        JedisPubSub jps = new JedisPubSub() {
            /**
             * JedisPubSub类是一个没有抽象方法的抽象类,里面方法都是一些空实现
             * 所以可以选择需要的方法覆盖,这儿使用的是SUBSCRIBE指令，所以覆盖了onMessage
             * 如果使用PSUBSCRIBE指令，则覆盖onPMessage方法
             * 当然也可以选择BinaryJedisPubSub,同样是抽象类，但方法参数为byte[]
             */
            @Override
            public void onMessage(String channel, String message) {
                if(Publisher.CHANNEL_KEY.equals(channel)) {
                    System.out.println("接收到消息: channel : " + message);
                    // 接收到exit消息后退出
                    if(EXIT_COMMAND.equals(message)) {
                        System.exit(0);
                    }

                }
            }

            /**
             * 订阅时
             */
            @Override
            public void onSubscribe(String channel, int subscribedChannels) {
                if(Publisher.CHANNEL_KEY.equals(channel)) {
                    System.out.println("订阅了频道:" + channel);
                }
            }
        };
        // 可以订阅多个频道 当前线程会阻塞在这儿
        jedis.subscribe(jps, channel);
    }

    // 先运行client，再运行Publisher进行消息发送
    public static void main(String[] args) {
        SubscribeClient client = new SubscribeClient();
        client.subscribe(Publisher.CHANNEL_KEY);
        // 并没有 unsubscribe方法
        // 相应的也没有punsubscribe方法
    }


//    总结：
//    使用Redis的List数据结构可以简单迅速地做一个消息队列，同时Redis提供的BRPOP和BLPOP等指令解决了频繁调用Jedis的rpop和lpop方法造成的资源浪费问题。
//    除此之外，Redis提供对发布/订阅模式的指令，可以实现消息传递、进程间通信。

}
