package com.hz.learnboot.jpa.service.impl;

import com.hz.learnboot.jpa.dao.UserInfoRepository;
import com.hz.learnboot.jpa.dao.WeiboRepository;
import com.hz.learnboot.jpa.domain.UserInfo;
import com.hz.learnboot.jpa.domain.Weibo;
import com.hz.learnboot.jpa.service.WeiboService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @Author hezhao
 * @Time 2018-07-01 21:56
 */
@Service
public class WeiboServiceImpl implements WeiboService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private WeiboRepository weiboRepository;

    @Override
    public List<Weibo> getUserWeibo(String userName) {
        Sort sort = Sort.by(new Sort.Order(Sort.Direction.DESC, "id"));
        return weiboRepository.searchUserWeibo(userName, sort);
    }

    @Override
    public Page<Weibo> searchWeibo(String userName, String weiboText, int pageNo, int pageSize) {
        UserInfo user = userInfoRepository.findByUserName(userName);
        if (user != null) {
            // pageNo从0开始
            return weiboRepository.findByUserInfoIsAndWeiboTextContaining(user, "%$weiboText%", PageRequest.of(pageNo, pageSize));
        }
        return new PageImpl(Collections.emptyList());
    }

    @Override
    public Page<Weibo> searchWeiboByCriteria(String userName, String weiboText, Date startDate, Date endDate, int pageNo, int pageSize) {
        return weiboRepository.findAll(
                (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.isNotBlank(userName)) {
                predicates.add(criteriaBuilder.equal(root.get("userInfo").get("userName"), userName));
            }
            if (StringUtils.isNotBlank(weiboText)) {
                predicates.add(criteriaBuilder.like(root.get("weiboText"), "%$weiboText%"));
            }
            if (startDate != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("createDate"), startDate));
            }
            if (endDate != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("createDate"), endDate));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        },  PageRequest.of(pageNo, pageSize));
    }

    @Override
    public List<Weibo> searchWeiboByExampleMatcher(String userName, String weiboText) {
        //创建查询条件数据对象
        Weibo weibo = new Weibo();
        weibo.setUserInfo(new UserInfo());

        if(userName != null && StringUtils.isNotBlank(userName)){
            weibo.getUserInfo().setUserName(userName);
        }
        if(weiboText != null && StringUtils.isNotBlank(weiboText)) {
            weibo.setWeiboText(weiboText);
        }

        //创建匹配器，即如何使用查询条件
        ExampleMatcher matcher = ExampleMatcher.matching() //构建对象
                // .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING) //改变默认字符串匹配方式：模糊查询
                // .withIgnoreCase(true) //改变默认大小写忽略方式：忽略大小写
                .withMatcher("weiboText", ExampleMatcher.GenericPropertyMatchers.contains());// 微博正文采用“包含”的方式查询
                // .withIgnorePaths("xxx")  //忽略属性

        //创建实例
        Example<Weibo> ex = Example.of(weibo, matcher);

        //查询
        return weiboRepository.findAll(ex);
    }
}
