package com.hz.learnboot.tkmybatis.controller;

import com.hz.learnboot.tkmybatis.dao.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserInfoController {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @GetMapping
    public Object list() {
        return userInfoMapper.selectAll();
    }

}
