package com.hz.learnboot.mybatis.dao;

import com.hz.learnboot.mybatis.domain.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/** 用户 DAO 接口类
 * @Author hezhao
 * @Time 2018-06-30 23:17
 */
public interface UserInfoMapper {
    /**
     * 根据用户名获取用户信息
     *
     * @param userName
     * @return
     */
    UserInfo findByUserName(@Param("userName") String userName);

    /**
     * 查询所有
     * @return
     */
    List<UserInfo> findAll();
}
