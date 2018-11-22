package com.hz.learnboot.jpa.service;

import com.hz.learnboot.jpa.domain.Comment;

import java.util.Date;
import java.util.List;

/**
 * @Author hezhao
 * @Time 2018-07-01 21:35
 */
public interface CommentService {
    List<Comment> searchComment(String userName, String weiboText, Date startDate, Date endDate, Integer pageNo, Integer pageSize);
}
