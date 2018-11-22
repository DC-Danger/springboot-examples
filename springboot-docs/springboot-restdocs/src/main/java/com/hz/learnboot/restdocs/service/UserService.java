package com.hz.learnboot.restdocs.service;

import com.hz.learnboot.restdocs.domain.User;

import java.util.List;

/**
 * 用户服务
 * 
 * @Author hezhao
 * @Time 2018-07-14 15:22
 */
public interface UserService {

    /**
     * 根据条件查询用户
     *
     * @param paramUser
     * @return
     */
    List<User> getUsersByParam(User paramUser);

    /**
     *
     * 根据Id获取用户
     *
     * @param id
     * @return
     */
    User getUserById(int id);

    /**
     * 添加用户
     *
     * @return
     */
    User addUser();

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    boolean removeUser(Integer id);

}
