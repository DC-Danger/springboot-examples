package com.hz.learnboot.jpa.dao;

import com.hz.learnboot.jpa.domain.UserInfo;
import com.hz.learnboot.jpa.domain.Weibo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/** 微博 Dao
 * @Author hezhao
 * @Time 2018-07-01 20:59
 */
public interface WeiboRepository extends JpaRepository<Weibo, Long>, JpaSpecificationExecutor<Weibo> {
    @Query("select w from Weibo w where w.userInfo.userName = :userName")
    List<Weibo> searchUserWeibo(@Param("userName") String username);

    /**
     * 排序
     */
    @Query("select w from Weibo w where w.userInfo.userName = :userName")
    List<Weibo> searchUserWeibo(@Param("userName") String username, Sort sort);

    @Modifying
    @Transactional(readOnly = false)
    @Query("update Weibo w set w.weiboText = :text where w.userInfo = :userInfo")
    int setUserWeiboContent(@Param("text") String weiboText, @Param("userInfo") UserInfo userInfo);

    /**
     * 分页查询
     */
    Page<Weibo> findByUserInfoIsAndWeiboTextContaining(UserInfo user, String weiboText, Pageable pageable);

    @Transactional(readOnly = false)
    int deleteByUserInfo(UserInfo userInfo);
}
