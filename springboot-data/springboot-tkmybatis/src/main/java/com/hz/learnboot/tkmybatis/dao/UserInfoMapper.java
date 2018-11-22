package com.hz.learnboot.tkmybatis.dao;

import com.hz.learnboot.tkmybatis.config.BaseMapper;
import com.hz.learnboot.tkmybatis.domain.UserInfo;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

/**
 *  使用 TkMybatis Mapper
 *
 * @Author hezhao
 * @Time 2018-07-12 15:17
 */
public interface UserInfoMapper extends BaseMapper<UserInfo> {

    // 自定义模糊查询，@Result 修饰返回的结果集
    @Select("<script>"+
            "SELECT id, name, user_name, password FROM user_info "+
            "WHERE 1=1 "+
            "<if test=\"name != null and name != '' \"> AND name like CONCAT('%',#{name},'%') </if>"+
            "<if test=\"userName != null and userName != '' \"> AND user_name = #{userName} </if>"+
            "<if test=\"id != null\"> AND id = #{id} </if>"+
            "</script>")
    @Results({
            @Result(property = "id", column = "id", id = true, jdbcType = JdbcType.BIGINT, javaType = Long.class),
            @Result(property = "userName", column = "user_name")
    })
    List<UserInfo> queryUserList(UserInfo userInfo);

    @Update("UPDATE user_info SET name=#{name} WHERE user_name =#{userName}")
    int updateUser(UserInfo userInfo);
}
