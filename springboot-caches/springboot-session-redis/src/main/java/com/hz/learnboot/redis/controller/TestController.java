package com.hz.learnboot.redis.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 把项目放到两个tomcat中运行，看测试的情况。
 *
 * Created by hezhao on 2018-07-06 18:26
 */
@RestController
@RequestMapping("/")
public class TestController {

    @GetMapping(value="/getSessionId")
    public String getSessionId(HttpServletRequest request){
        HttpSession session = request.getSession();

        Object o = session.getAttribute("msg");
        if(o == null){
            o = "spring boot 牛逼了!!!由端口" + request.getLocalPort() + "生成";
            session.setAttribute("msg", o);
        }

        String str = "端口=" + request.getLocalPort() +  " sessionId=" + request.getSession().getId() +"<br/>";
        return str + o;
    }

}
