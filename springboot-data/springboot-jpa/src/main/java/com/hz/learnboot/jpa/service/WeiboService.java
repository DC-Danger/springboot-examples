package com.hz.learnboot.jpa.service;

import com.hz.learnboot.jpa.domain.Weibo;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.List;

/**
 * @Author hezhao
 * @Time 2018-07-01 21:35
 */
public interface WeiboService {
    List<Weibo> getUserWeibo(String userName);

    Page<Weibo> searchWeibo(String userName, String weiboText, int pageNo, int pageSize);

    /**
     * JPA Criteria 动态查询
     */
    Page<Weibo> searchWeiboByCriteria(String userName, String weiboText, Date startDate, Date endDate, int pageNo, int pageSize);

    /**
     * 实例查询
     * 参考：https://www.cnblogs.com/rulian/p/6533109.html
     */
    List<Weibo> searchWeiboByExampleMatcher(String userName, String weiboText);
}
