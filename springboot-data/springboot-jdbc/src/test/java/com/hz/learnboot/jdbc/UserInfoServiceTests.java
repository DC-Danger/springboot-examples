package com.hz.learnboot.jdbc;

import com.hz.learnboot.jdbc.domain.UserInfo;
import com.hz.learnboot.jdbc.service.UserInfoService;
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
    public void testQueryAll() {
        List<UserInfo> userList = userInfoService.queryAll();
        System.out.println("==================================testQueryAll==================================");
        userList.forEach(System.out::println);
        System.out.println("==================================testQueryAll==================================");
    }

    @Test
    public void testQueryByUserName() {
        UserInfo user = userInfoService.queryByUserName("admin");
        System.out.println("==================================testQueryByUserName==================================");
        System.out.println(user);
        System.out.println("==================================testQueryByUserName==================================");
    }

    @Test
    public void testInsert() {
        userInfoService.insert(UserInfo.builder().name("CC").userName("cc").password("123").build());
        userInfoService.insert(UserInfo.builder().name("DD").userName("dd").password("123").build());
        System.out.println("==================================testInsert==================================");
        System.out.println("insert count is " + 2);
    }

    @Test
    public void testUpdateByUserName() {
        int count = userInfoService.updateByUserName(UserInfo.builder().name("AA Updated2").userName("aa").password("123").build());
        System.out.println("==================================testUpdateByUserName==================================");
        System.out.println("update count is " + count);
    }

    @Test
    public void testDeleteByUserName() {
        userInfoService.deleteByUserName("admin");
        System.out.println("==================================testDeleteByUserName==================================");
        System.out.println("delete count is " + 1);
    }

}
