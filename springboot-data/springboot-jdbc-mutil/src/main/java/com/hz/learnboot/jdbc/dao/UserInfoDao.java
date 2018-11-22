package com.hz.learnboot.jdbc.dao;

import com.hz.learnboot.jdbc.domain.UserInfo;

import java.util.List;

/** 用户Dao
 * @Author hezhao
 * @Time 2018-06-30 23:17
 */
public interface UserInfoDao{

    List<UserInfo> queryAll();

    UserInfo queryByUserName(String userName);

    int insert(UserInfo userInfo);

    int updateByUserName(UserInfo userInfo);

    int deleteByUserName(String userName);
}
