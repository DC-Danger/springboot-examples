package com.hz.learnboot.security.sevice;

import com.hz.learnboot.security.entity.UserInfo;

/**
 * 用户信息服务类
 */
public interface UserInfoService {
    /**
     * 通过username查找用户信息
     *
     * @param username
     * @return
     */
    UserInfo findByUsername(String username);
}