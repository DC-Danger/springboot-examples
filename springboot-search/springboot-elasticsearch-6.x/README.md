# 注意事项
> 由于目前最新的spring-data-elasticsearch:3.0.8.RELEASE 不支持最新的elasticsearch6.x版本。
所以采用手动配置的方式集成。    

## [参考网址：https://blog.csdn.net/chy2z/article/details/80461745](https://blog.csdn.net/chy2z/article/details/80461745)

ES版本：6.3.0

日期：2018-07-20

## 问题记录：
```
2018-07-20 18:53:50.538  WARN 7588 --- [main] ConfigServletWebServerApplicationContext : Exception encountered during context initialization - cancelling refresh attempt: org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'elasticsearchUtil': Unsatisfied dependency expressed through field 'transportClient'; nested exception is org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'transportClient' defined in class path resource [com/hz/learnboot/es/config/ElasticsearchConfig.class]: Bean instantiation via factory method failed; nested exception is org.springframework.beans.BeanInstantiationException: Failed to instantiate [org.elasticsearch.client.transport.TransportClient]: Factory method 'transportClient' threw exception; nested exception is java.lang.NoSuchFieldError: Shared
Caused by: org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'transportClient' defined in class path resource [com/hz/learnboot/es/config/ElasticsearchConfig.class]: Bean instantiation via factory method failed; nested exception is org.springframework.beans.BeanInstantiationException: Failed to instantiate [org.elasticsearch.client.transport.TransportClient]: Factory method 'transportClient' threw exception; nested exception is java.lang.NoClassDefFoundError: org/elasticsearch/common/transport/InetSocketTransportAddress
Caused by: java.lang.ClassNotFoundException: org.elasticsearch.common.transport.InetSocketTransportAddress
```
- https://www.baidu.com/s?wd=Failed%20to%20instantiate%20%5Borg.elasticsearch.client.transport.TransportClient%5D%3A%20Factory%20method%20%27transpor&rsv_spt=1&rsv_iqid=0x8948a4b00000a695&issp=1&f=8&rsv_bp=1&rsv_idx=2&ie=utf-8&rqlang=cn&tn=baiduhome_pg&rsv_enter=0&oq=nested%2520exception%2520is%2520java.lang.NoSuchField%2526gt%253Brror%253A%2520Shared&inputT=1323&rsv_t=66f4%2F9Kc%2FwQSTo4AxwBXC37khNvw3dtzNrdSv1hiZR22TQdz1Xt7gPJiFqv5lVI3v7kB&rsv_sug3=3&rsv_n=2&rsv_pq=9557f90d0000b4e3&rsv_sug4=1409
- https://elasticsearch.cn/question/1203
- https://www.imooc.com/qadetail/256945?lastmedia=1

- 应该还是版本问题，6.3版本去掉了InetSocketTransportAddress这个类

解决方法：
6.x之后是使用更高效的netty4做异步通信的。
Maven引入依赖：
```
<dependency>
    <groupId>org.elasticsearch.plugin</groupId>
    <artifactId>transport-netty4-client</artifactId>
    <version>6.3.0</version>
</dependency>
```