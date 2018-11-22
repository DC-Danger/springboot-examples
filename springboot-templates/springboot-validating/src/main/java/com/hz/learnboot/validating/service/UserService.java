package com.hz.learnboot.validating.service;

import com.hz.learnboot.validating.domain.User;

import java.util.List;

/** User 业务层接口
 * Created by hezhao on 2018-06-29 09:17
 */
public interface UserService {
    List<User> findAll();

    User insertByUser(User user);

    User update(User user);

    User delete(Long id);

    User findById(Long id);
}
