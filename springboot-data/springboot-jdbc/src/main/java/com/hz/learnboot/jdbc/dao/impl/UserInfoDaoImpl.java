package com.hz.learnboot.jdbc.dao.impl;

import com.hz.learnboot.jdbc.domain.UserInfo;
import com.hz.learnboot.jdbc.dao.UserInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

/** 用户Dao
 * @Author hezhao
 * @Time 2018-06-30 23:17
 */
@Repository
public class UserInfoDaoImpl extends JdbcDaoSupport implements UserInfoDao {

    // 因为继承了JdbcDaoSupport，所以必须手动注入DataSource
    @Autowired
    public void setDs(DataSource ds){
        this.setDataSource(ds);
    }

    @Override
    public List<UserInfo> queryAll() {
        String sql = "select * from user_info";
        // Spring 2.5 提供了一个便利的RowMapper实现-----BeanPropertyRowMapper
        // 它可自动将一行数据映射到指定类的实例中 它首先将这个类实例化，然后通过名称匹配的方式，映射到属性中去。
        // 自动绑定，需要列名称和Java实体类名字一致，如：属性名 “userName” 可以匹配数据库中的列字段 "USERNAME" 或 “user_name”。
        return this.getJdbcTemplate().query(sql, new BeanPropertyRowMapper<>(UserInfo.class));
    }

    @Override
    public UserInfo queryByUserName(String userName) {
        String sql = "select * from user_info where user_name = ?";
        return this.getJdbcTemplate().queryForObject(sql, new BeanPropertyRowMapper<>(UserInfo.class), userName);
    }

    @Override
    public int insert(UserInfo userInfo) {
        String sql = "INSERT INTO user_info (name, user_name, password) VALUES (?,?,?)";
        return this.getJdbcTemplate().update(sql, userInfo.getName(), userInfo.getUserName(), userInfo.getPassword());
    }

    @Override
    public int updateByUserName(UserInfo userInfo) {
        String sql = "update user_info set name=?,password=? where user_name=?";
        return this.getJdbcTemplate().update(sql, userInfo.getName(), userInfo.getPassword(), userInfo.getUserName());
    }

    @Override
    public int deleteByUserName(String userName) {
        String sql = "delete from user_info where user_name=?";
        return this.getJdbcTemplate().update(sql, userName);
    }
}
