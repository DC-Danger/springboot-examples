package com.hz.learnboot.validating.service.impl;

import com.hz.learnboot.validating.dao.UserRepository;
import com.hz.learnboot.validating.domain.User;
import com.hz.learnboot.validating.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/** User 业务层实现
 * Created by hezhao on 2018-06-29 09:18
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User insertByUser(User user) {
        logger.info("新增用户：" + user);
        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        logger.info("更新用户：" + user);
        return userRepository.save(user);
    }

    @Override
    public User delete(Long id) {
        User user = userRepository.findById(id).get();
        userRepository.delete(user);

        logger.info("删除用户：" + user);
        return user;
    }

    @Override
    public User findById(Long id) {
        User user = userRepository.findById(id).get();
        logger.info("根据ID：{} 获取用户信息：{}", id, user);
        return user;
    }
}
