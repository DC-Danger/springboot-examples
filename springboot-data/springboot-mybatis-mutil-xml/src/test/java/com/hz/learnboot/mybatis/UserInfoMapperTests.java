package com.hz.learnboot.mybatis;

import com.github.pagehelper.PageHelper;
import com.hz.learnboot.mybatis.dao.master.UserInfoMapper;
import com.hz.learnboot.mybatis.domain.master.UserInfo;
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
    public void testFindByUserName() {
        UserInfo user = userInfoMapper.findByUserName("admin");
        System.out.println("==================================testFindByUserName==================================");
        System.out.println(user);
        System.out.println("==================================testFindByUserName==================================");
    }

    @Test
    public void testFindAll() {
        List<UserInfo> users = userInfoMapper.findAll();
        System.out.println("==================================testFindAll==================================");
        System.out.println(users);
        System.out.println("==================================testFindAll==================================");
    }

    @Test
    public void testFindAllByPage() {
        List<UserInfo> pageInfo = getListByPage(2, 1);
        System.out.println("==================================testFindAllByPage==================================");
        pageInfo.forEach(e -> System.out.println(e));
        System.out.println("==================================testFindAllByPage==================================");
    }

    private List<UserInfo> getListByPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<UserInfo> users = userInfoMapper.findAll();
        return users;
    }

}
