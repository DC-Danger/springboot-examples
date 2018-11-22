package com.hz.learnboot.jdbc.dao.impl;

import com.hz.learnboot.jdbc.domain.UserInfo;
import com.hz.learnboot.jdbc.dao.UserInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

/** 用户Dao
 * @Author hezhao
 * @Time 2018-06-30 23:17
 */
@Repository
public class UserInfoDaoImpl implements UserInfoDao {

    // 指定jdbcTemplate
    @Autowired
    @Qualifier("primaryJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<UserInfo> queryAll() {
        String sql = "select * from user_info";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(UserInfo.class));
    }

    @Override
    public UserInfo queryByUserName(String userName) {
        String sql = "select * from user_info where user_name = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(UserInfo.class), userName);
    }

    @Override
    public int insert(UserInfo userInfo) {
        String sql = "INSERT INTO user_info VALUES (null,?,?,?)";
        return jdbcTemplate.update(sql, userInfo.getName(), userInfo.getUserName(), userInfo.getPassword());
    }

    @Override
    public int updateByUserName(UserInfo userInfo) {
        String sql = "update user_info set name=?,password=? where user_name=?";
        return jdbcTemplate.update(sql, userInfo.getName(), userInfo.getPassword(), userInfo.getUserName());
    }

    @Override
    public int deleteByUserName(String userName) {
        String sql = "delete from user_info where user_name=?";
        return jdbcTemplate.update(sql, userName);
    }
}
