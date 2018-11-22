package com.hz.learnboot.mybatis.controller;

import com.hz.learnboot.mybatis.dao.master.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hezhao on 2018-07-13 13:29
 */
@RestController
@RequestMapping("/user")
public class UserInfoController {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @GetMapping
    public Object list() {
        return userInfoMapper.queryAll();
    }

}
