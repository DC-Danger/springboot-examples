package com.hz.learnboot.cloud.eureka.controller;

import com.hz.learnboot.cloud.eureka.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * 测试控制器
 *
 * Created by hezhao on 2018-08-01 11:50
 */
@RestController
@RequestMapping("/person-rest")
public class PersonController {

    @Autowired
    private RestTemplate restTemplate;

//    @RequestMapping("/getPersonById/{id}")
//    public Person getPersonById(@PathVariable("id") Long id) throws URISyntaxException {
//		// 没有使用 Eureka 时，uri 为消息提供者的地址，需要指定 ip 和 端口
//		return restTemplate.getForObject(new URI("http://localhost:8762/person/getPersonById/" + id), Person.class);
//	}

    // 注意：此处只是为了体现服务发现的效果，实际开发中不要使用 DiscoveryClient 查询服务进行调用
    // 而是使用Ribbon 或 Feign
    @Autowired
    private DiscoveryClient client;

    @RequestMapping("/getPersonById/{id}")
    public Person getPersonById(@PathVariable("id") Long id) {

        List<ServiceInstance> list = this.client.getInstances("SERVICE-PROVIDER");
        String uri = "";
        for (ServiceInstance instance : list) {
            if (instance.getUri() != null && !"".equals(instance.getUri())) {
                uri = instance.getUri().toString();
                break;
            }
        }
        Person person = restTemplate.getForObject(uri + "/person/getPersonById/" + id, Person.class);
        return person;
    }

}
