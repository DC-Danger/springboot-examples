package com.hz.learnboot.mybatis;

import com.hz.learnboot.mybatis.dao.UserInfoMapper;
import com.hz.learnboot.mybatis.domain.UserInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserInfoMapperTests {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Test
    public void testQueryAll() {
        List<UserInfo> userList = userInfoMapper.queryAll();
        System.out.println("==================================testQueryAll==================================");
        userList.forEach(System.out::println);
        System.out.println("==================================testQueryAll==================================");
    }

    @Test
    public void testQueryUserList() {
        UserInfo userInfo = UserInfo.builder().name("b").build();
        List<UserInfo> userList = userInfoMapper.queryUserList(userInfo);
        System.out.println("==================================testQueryUserList==================================");
        userList.forEach(System.out::println);
        System.out.println("==================================testQueryUserList==================================");
    }

    @Test
    public void testGetOne() {
        UserInfo user = userInfoMapper.getOne(1L);
        System.out.println("==================================testGetOne==================================");
        System.out.println(user);
        System.out.println("==================================testGetOne==================================");
    }

    @Test
    public void testInsertUser() {
        int count = userInfoMapper.insertUser(UserInfo.builder().name("AA").userName("aa").password("123").build());
        count += userInfoMapper.insertUser(UserInfo.builder().name("BB").userName("bb").password("123").build());
        System.out.println("==================================testInsertUser==================================");
        System.out.println("insert count is " + count);
    }

    @Test
    public void testUpdateUser() {
        int count = userInfoMapper.updateUser(UserInfo.builder().name("AA Updated").userName("aa").build());
        System.out.println("==================================testUpdateUser==================================");
        System.out.println("update count is " + count);
    }

    @Test
    public void testDeleteUser() {
        int count = userInfoMapper.deleteUser(7L);
        System.out.println("==================================testDeleteUser==================================");
        System.out.println("delete count is " + count);
    }

    @Test
    public void testSearchUser() {
        UserInfo userInfo = UserInfo.builder().id(1L).name("ja").userName("admin").build();
        List<UserInfo> userList = userInfoMapper.searchUser(userInfo);
        System.out.println("==================================testSearchUser==================================");
        userList.forEach(System.out::println);
        System.out.println("==================================testSearchUser==================================");
    }

    @Test
    public void testFindUser() {
        UserInfo userInfo = UserInfo.builder().id(1L).name("ja").userName("admin").build();
        List<UserInfo> userList = userInfoMapper.findUser(userInfo);
        System.out.println("==================================testFindUser==================================");
        userList.forEach(System.out::println);
        System.out.println("==================================testFindUser==================================");
    }

}
