package com.hz.learnboot.jpa.dao;

import com.hz.learnboot.jpa.domain.Comment;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/** 评论Dao
 * @Author hezhao
 * @Time 2018-07-01 21:08
 */
@Repository
public class CommentRepository {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * 使用最为基础的方法去写DAO
     */
    @Transactional(readOnly = true)
    public List<Comment> searchComment(String userName, String weiboText, Date startDate, Date endDate, Integer pageNo, Integer pageSize) {
        // JPQL
        StringBuilder jpql = new StringBuilder("select c from Comment c join fetch c.userInfo u left join fetch c.weibo w where 1=1 ");
        Map<String, Object> paramMap = new HashMap<>();
        if (StringUtils.isNotBlank(userName)) {
            jpql.append(" and u.userName = :userName");
            paramMap.put("userName", userName);
        }
        if (StringUtils.isNotBlank(weiboText)) {
            jpql.append(" and w.weiboText like :weiboText");
            paramMap.put("weiboText", "%" + weiboText + "%");
        }
        if (startDate != null) {
            jpql.append(" and c.commentDate >= :startDate");
            paramMap.put("startDate", startDate);
        }
        if (endDate != null) {
            jpql.append(" and c.commentDate <= :endDate");
            paramMap.put("endDate", endDate);
        }

        Query query = entityManager.createQuery(jpql.toString());
        Set<String> keys = paramMap.keySet();
        for (String keyItem : keys) {
            query.setParameter(keyItem, paramMap.get(keyItem));
        }

        if(pageNo != null && pageSize != null){
            int start = (pageNo - 1) * pageSize;
            if (start < 0){
                start = 0;
            }
            return query.setFirstResult(start).setMaxResults(pageSize).getResultList();
        } else {
            return query.getResultList();
        }
    }
}
