package com.hz.learnboot.shiro.sevice.impl;

import com.hz.learnboot.shiro.dao.UserInfoDao;
import com.hz.learnboot.shiro.entity.UserInfo;
import com.hz.learnboot.shiro.sevice.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户信息服务实现类
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    private Logger logger = LoggerFactory.getLogger(UserInfoServiceImpl.class);

    @Resource
    private UserInfoDao userInfoDao;

    @Override
    public UserInfo findByUsername(String username) {
        logger.info("UserInfoServiceImpl.findByUsername()-->" + username);
        return userInfoDao.findByUsername(username);
    }
}