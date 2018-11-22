package com.hz.learnboot.jdbc.service;

import com.hz.learnboot.jdbc.domain.UserInfo;

import java.util.List;

/** UserInfoService
 * @Author hezhao
 * @Time 2018-07-01 21:35
 */
public interface UserInfoService {
    List<UserInfo> queryAll();

    UserInfo queryByUserName(String userName);

    int insert(UserInfo userInfo);

    int updateByUserName(UserInfo userInfo);

    int deleteByUserName(String userName);
}
