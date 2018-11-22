# Kafka总结

## Topic
- Kafka将消息以topic为单位进行归纳。

- 一个topic是对一组消息的归纳。对每个topic，Kafka 对它的日志进行了分区。

## Group
在Kafka中理解Group的机制很重要。要知道，在Kafka中其实只有一种模式，那就是发布/订阅模式，但是可以通过调整Group来实现不同的需求。

- Consumers可以加入一个consumer 组，共同竞争一个topic，topic中的消息将被分发到组中的一个成员中。

- 同一组中的consumer可以在不同的程序中，也可以在不同的机器上。

- 如果所有的consumer都在一个组中，这就成为了传统的队列模式，在各consumer中实现负载均衡。

- 如果所有的consumer都不在不同的组中，这就成为了发布-订阅模式，所有的消息都被分发到所有的consumer中。

- 更常见的是，每个topic都有若干数量的consumer组，每个组都是一个逻辑上的“订阅者”，为了容错和更好的稳定性，每个组由若干consumer组成。这其实就是一个发布-订阅模式，只不过订阅者是个组而不是单个consumer。
