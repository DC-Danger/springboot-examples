package com.hz.learnboot.reverseproxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot 应用启动类
 *
 * 通过node.js的反向代理实现跨域
 * node.js提供了一些反向代理的中间件，能轻而易举的实现跨域，而不需要spring boot做任何设置。
 * 安装express-http-proxy中间件
 * npm install --save-dev express-http-proxy
 *
 * 参考：https://www.cnblogs.com/GoodHelper/p/6824562.html
 *
 * Created by hezhao on 28/06/2018.
 */
@SpringBootApplication
public class SpringBootReverseProxyApplication {

    /*
    通过反向代理的方式是最佳方案。
    在正式项目中，可以使用node.js控制web前端渲染与spring boot后端提供API服务的组合。
    这样，可以控制用户在node.js端登录后才能调用spring boot的API服务。
    在大型web项目中也可以使用node.js的反向代理，把很多子站点关联起来，这样便发挥出了网站灵活的扩展性。
    除了使用node.js的中间件实现跨越以外，同样能借助nginx等http反向代理服务器实现跨越。
    */
    public static void main(String[] args) {
        SpringApplication.run(SpringBootReverseProxyApplication.class, args);
    }
}
