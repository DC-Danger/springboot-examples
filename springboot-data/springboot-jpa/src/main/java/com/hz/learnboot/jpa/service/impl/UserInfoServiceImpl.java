package com.hz.learnboot.jpa.service.impl;

import com.hz.learnboot.jpa.dao.UserInfoRepository;
import com.hz.learnboot.jpa.domain.UserInfo;
import com.hz.learnboot.jpa.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author hezhao
 * @Time 2018-07-01 21:43
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Override
    public List<UserInfo> queryUserList() {
        return userInfoRepository.findAll();
    }

    @Override
    public UserInfo queryUserById(Long id) {
        return userInfoRepository.findById(id).orElse(null);
    }

    @Override
    public UserInfo queryUserByUserName(String userName) {
        return userInfoRepository.findByUserName(userName);
    }

    @Override
    public List<UserInfo> queryUserByNameNativeQuery(String name) {
        return userInfoRepository.selectUserInfoByNameNativeQuery(name);
    }

    @Override
    public List<UserInfo> queryUserByName(String name) {
        return userInfoRepository.selectUserInfoByName(name);
    }

    @Override
    public List<UserInfo> searchUserName(String userName) {
        return userInfoRepository.searchUserName(userName);
    }

    @Override
    public UserInfo saveUser(UserInfo userInfo) {
        return userInfoRepository.save(userInfo);
    }

    @Override
    public int updateNameByUserName(UserInfo userInfo) {
        return userInfoRepository.updateNameByUserName(userInfo.getName(), userInfo.getUserName());
    }

    @Override
    public void deleteUser(Long id) {
        userInfoRepository.deleteById(id);
    }

    @Override
    public int deleteUserByUserName(String userName) {
        return userInfoRepository.deleteUserInfoByUserName(userName);
    }
}
