package com.hz.learnboot.restdocs.service.impl;

import com.hz.learnboot.restdocs.domain.User;
import com.hz.learnboot.restdocs.service.UserService;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 这里简单模拟一下数据库
 *
 * @Author hezhao
 * @Time 2018-07-14 10:09
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private static final List<User> userList = new ArrayList<>();
    private static final String[] names = new String[]{"张三", "李四", "王二麻子", "赵一", "钱一", "孙一", "诸葛孔明", "刘备", "张飞", "曹操"};
    private static final Date now = new Date();

    static {
        // 初始化数据
        int forMaxIndex = new Random().nextInt(50);
        for (int i = 0; i < forMaxIndex; i++) {
            userList.add(User.builder().id(i).name(names[i % 10]).age(20 + i).birthday(DateUtils.addYears(now, -(20 + i))).build());
        }
    }

    @Override
    public List<User> getUsersByParam(User paramUser) {
        if (paramUser != null) {
            List<User> users = new ArrayList<>();
            boolean isAgeEquals, isNameEquals;
            for (User user : userList) {
                isAgeEquals = false;
                isNameEquals = false;

                if (paramUser.getAge() != null) {
                    if (user.getAge().equals(paramUser.getAge())) {
                        isAgeEquals = true;
                    }
                } else {
                    isAgeEquals = true;
                }
                if (paramUser.getName() != null) {
                    if (user.getName().equals(paramUser.getName())) {
                        isNameEquals = true;
                    }
                } else {
                    isNameEquals = true;
                }
                if (isAgeEquals && isNameEquals) {
                    users.add(user);
                }
            }
            return users;
        }
        return userList;
    }

    @Override
    public User getUserById(int id) {
        for (User user : userList) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    @Override
    public User addUser() {
        int index = userList.size();
        User user = User.builder().id(index).name(names[index % 10]).age(20 + index).birthday(DateUtils.addYears(now, -(20 + index))).build();
        userList.add(user);
        return user;
    }

    @Override
    public boolean removeUser(Integer id) {
        Iterator<User> iterator = userList.iterator();
        while (iterator.hasNext()) {
            User user = iterator.next();
            if (user.getId() == id) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

}
