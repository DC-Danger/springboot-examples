package com.hz.learnboot.jpa.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/** 微博
 * @Author hezhao
 * @Time 2018-06-30 22:37
 */
@Data @Builder @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "weibo")
public class Weibo {

    @Id
    @Column(name= "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name= "weibo_text", nullable = false)
    private String weiboText;

    @Column(name= "create_date", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    // 多对一
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserInfo userInfo;

    /// 一对多
    // mappedBy 把关系的维护交给多方对象的属性去维护关系, 只有OneToOne,OneToMany,ManyToMany上才有mappedBy属性，ManyToOne不存在该属性
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE}, mappedBy = "weibo")
    @JsonIgnore
    private Set<Comment> comments;

    @Override
    public String toString() {
        return "Weibo{" +
                "id=" + id +
                ", weiboText='" + weiboText + '\'' +
                ", createDate=" + createDate +
                '}';
    }
}
