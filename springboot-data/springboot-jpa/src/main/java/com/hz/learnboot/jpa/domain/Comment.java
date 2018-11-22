package com.hz.learnboot.jpa.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/** 微博评论
 * @Author hezhao
 * @Time 2018-06-30 22:37
 */
@Data @Builder @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @Column(name= "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name= "comment_text", nullable = false)
    private String commentText;

    @Column(name= "comment_date", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date commentDate;

    // 多对一
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserInfo userInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "weibo_id")
    private Weibo weibo;

}
