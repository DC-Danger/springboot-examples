package com.hz.learnboot.jpa.dao;

import com.hz.learnboot.jpa.domain.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/** 用户Dao
 * @Author hezhao
 * @Time 2018-06-30 23:17
 */
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {

    /**
     * 根据username查询UserInfo，不需要写实现, JPA根据自定义查询时的命名规则去实现
     */
    UserInfo findByUserName(String username);

    // 原生SQL查询
    // 索引参数,索引值从1开始，查询中'?x'的个数要和方法的参数个数一致，且顺序也要一致
    @Query(value = "SELECT * FROM user_info WHERE name like %?1%", nativeQuery = true)
    List<UserInfo> selectUserInfoByNameNativeQuery(String name);

    // JPQL查询, 类似于HQL
    // 命名参数方式（推荐），可以用':参数名'的形式，在方法参数中使用@Param（"参数名"）注解，这样就可以不用按顺序来定义形参
    @Query("from UserInfo u where u.name like %:name%")
    List<UserInfo> selectUserInfoByName(@Param("name") String name);

    // 命名查询
    List<UserInfo> searchUserName(@Param("userName") String userName);

    // 配合@Modifying注解一共使用，则可以完成数据的DELETE、UPDATE操作。注意: 不支持 INSERT
    // 默认情况下, SpringData 的每个方法上有事务, 但都是一个只读事务. 他们不能完成修改操作!
    // UPDATE 或 DELETE 操作必须需要使用事务, 在正式的开发环境建议在 Service 层的方法上添加事务操作
    @Modifying
    @Transactional(readOnly = false, timeout = 10)
    @Query("update UserInfo u set u.name=:name where u.userName=:userName")
    int updateNameByUserName(@Param("name") String name, @Param("userName") String userName);

    @Modifying
    @Transactional
    @Query("delete from UserInfo u where u.userName=:userName")
    int deleteUserInfoByUserName(@Param("userName") String userName);
}
