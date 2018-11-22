package com.hz.learnboot.jpa;

import com.hz.learnboot.jpa.domain.primary.UserInfo;
import com.hz.learnboot.jpa.service.UserInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserInfoServiceTests {

    @Autowired
    private UserInfoService userInfoService;

    @Test
    public void testQueryUserList() {
        List<UserInfo> userList = userInfoService.queryUserList();
        System.out.println("==================================testQueryUserList==================================");
        userList.forEach(System.out::println);
        System.out.println("==================================testQueryUserList==================================");
    }

    @Test
    public void testQueryUserByName() {
        List<UserInfo> userList = userInfoService.queryUserByName("b");
        System.out.println("==================================testQueryUserByName==================================");
        userList.forEach(System.out::println);
        System.out.println("==================================testQueryUserByName==================================");
    }

    @Test
    public void testQueryUserById() {
        UserInfo user = userInfoService.queryUserById(1L);
        System.out.println("==================================testQueryUserById==================================");
        System.out.println(user);
        System.out.println("==================================testQueryUserById==================================");
    }

    @Test
    public void testSaveUser() {
        userInfoService.saveUser(UserInfo.builder().name("AA").userName("aa").password("123").build());
        userInfoService.saveUser(UserInfo.builder().name("BB").userName("bb").password("123").build());
        System.out.println("==================================testSaveUser==================================");
        System.out.println("insert count is " + 2);
    }

    @Test
    public void testUpdateNameByUserName() {
        int count = userInfoService.updateNameByUserName(UserInfo.builder().name("AA Updated").userName("aa").build());
        System.out.println("==================================testUpdateNameByUserName==================================");
        System.out.println("update count is " + count);
    }

    @Test
    public void testDeleteUser() {
        userInfoService.deleteUser(7L);
        System.out.println("==================================testDeleteUser==================================");
        System.out.println("delete count is " + 1);
    }

    @Test
    public void testSearchUserName() {
        List<UserInfo> userList = userInfoService.searchUserName("admin");
        System.out.println("==================================testSearchUserName==================================");
        userList.forEach(System.out::println);
        System.out.println("==================================testSearchUserName==================================");
    }

}
