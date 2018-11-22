package com.hz.learnboot.security.dao;

import com.hz.learnboot.security.entity.UserInfo;
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