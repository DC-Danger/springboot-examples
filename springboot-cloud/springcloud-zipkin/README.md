# zipkin示例由三个项目组成：

server-zipkin,它的主要作用使用ZipkinServer 的功能，收集调用数据，并展示；

service-hi,对外暴露hi接口；

service-miya,对外暴露miya接口；

这两个service可以相互调用；并且只有调用了，server-zipkin才会收集数据的，这就是为什么叫服务追踪了。


## Zipkin Server
在spring Cloud为F版本的时候，已经不需要自己构建Zipkin Server了，只需要下载jar即可，

下载地址：https://dl.bintray.com/openzipkin/maven/io/zipkin/java/zipkin-server/

下载完成jar 包之后，需要运行jar，如下：

`java -jar zipkin-server-2.10.4-exec.jar`

访问浏览器localhost:9411