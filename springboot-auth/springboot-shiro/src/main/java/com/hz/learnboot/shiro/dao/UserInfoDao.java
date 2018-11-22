package com.hz.learnboot.shiro.dao;

import com.hz.learnboot.shiro.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 用户信息数据
 */
public interface UserInfoDao extends JpaRepository<UserInfo,Long> {
    /**
     * 通过username查找用户信息
     * @param username
     * @return
     */
    UserInfo findByUsername(String username);
}