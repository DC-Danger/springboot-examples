package com.hz.learnboot.tkmybatis;

import com.google.common.collect.Lists;
import com.hz.learnboot.tkmybatis.dao.UserInfoMapper;
import com.hz.learnboot.tkmybatis.domain.UserInfo;
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
    public void testSelectAll() {
        List<UserInfo> userList = userInfoMapper.selectAll();
        System.out.println("==================================testSelectAll==================================");
        userList.forEach(System.out::println);
        System.out.println("==================================testSelectAll==================================");
    }

    @Test
    public void testSelect() {
        UserInfo userInfo = UserInfo.builder().name("bob").build();
        List<UserInfo> userList = userInfoMapper.select(userInfo);
        System.out.println("==================================testSelectUserList==================================");
        userList.forEach(System.out::println);
        System.out.println("==================================testSelectUserList==================================");
    }

    @Test
    public void testSelectOne() {
        UserInfo userInfo = UserInfo.builder().id(1L).build();
        UserInfo user = userInfoMapper.selectOne(userInfo);
        System.out.println("==================================testSelectOne==================================");
        System.out.println(user);
        System.out.println("==================================testSelectOne==================================");
    }

    @Test
    public void testInsertList() {
        List<UserInfo> list = Lists.newArrayList(
                UserInfo.builder().name("BB").userName("bb").password("123").build(),
                UserInfo.builder().name("AA").userName("aa").password("123").build()
        );

        int count = userInfoMapper.insertList(list);
        System.out.println("==================================testInsertList==================================");
        System.out.println("insert count is " + count);
    }

    @Test
    public void testUpdateByPrimaryKeySelective() {
        int count = userInfoMapper.updateByPrimaryKeySelective(UserInfo.builder().id(10L).name("BB Updated").userName("bb").password("123").build());
        System.out.println("==================================testUpdateByPrimaryKeySelective==================================");
        System.out.println("update count is " + count);
    }

    @Test
    public void testDeleteByIds() {
        int count = userInfoMapper.deleteByIds(String.valueOf(7L));
        System.out.println("==================================testDeleteByIds==================================");
        System.out.println("delete count is " + count);
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
    public void testUpdateUser() {
        int count = userInfoMapper.updateUser(UserInfo.builder().name("BB Updated").userName("bb").build());
        System.out.println("==================================testUpdateUser==================================");
        System.out.println("update count is " + count);
    }

}
