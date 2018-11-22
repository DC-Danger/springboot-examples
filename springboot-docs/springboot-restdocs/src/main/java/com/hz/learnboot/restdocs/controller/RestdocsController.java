package com.hz.learnboot.restdocs.controller;

import com.hz.learnboot.restdocs.domain.User;
import com.hz.learnboot.restdocs.service.UserService;
import com.hz.learnboot.restdocs.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
public class RestdocsController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/index", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Map<String, String> index() {
        return Collections.singletonMap("message", "Hello World");
    }

    @GetMapping(value = "/getUserById/{id}")
    public ResponseVo get(@PathVariable("id") int id) {
        return new ResponseVo<User>(200, "success", userService.getUserById(id));
    }

    @GetMapping(value = "/listUsers")
    public ResponseVo listUsers(User user) {
        return new ResponseVo<List>(200, "success", userService.getUsersByParam(user));
    }

    @RequestMapping(value = "/addUser")
    public ResponseVo addUser() {
        User user = userService.addUser();
        return new ResponseVo(200, "success", user);
    }

    @RequestMapping(value = "/removeUser/{id}")
    public ResponseVo removeUser(@PathVariable("id") int id) {
        boolean result = userService.removeUser(id);
        if (result) {
            return new ResponseVo(200, "success", "删除用户成功");
        }
        return new ResponseVo(500, "error", "删除用户失败");
    }

}
