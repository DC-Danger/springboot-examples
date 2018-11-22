package com.hz.learnboot.solr.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.SolrDocument;

import java.io.Serializable;

/**
 * 城市信息
 *
 * @Author hezhao
 * @Time 2018-06-30 23:53
 */
@Data @Builder @NoArgsConstructor @AllArgsConstructor
@SolrDocument(solrCoreName = "new_core")
public class City implements Serializable {
    private static final long serialVersionUID = -3322738983942005670L;

    /** 城市编号,主键必须加这两个注解 */
    @Id
    @Field
    private Long id;

    /** 省份编号 */
    @Field
    private Long provinceId;

    /**
     * 城市名称
     */
    @Field
    private String cityName;

    /**
     * 描述
     */
    @Field
    private String description;

    /** 城市评分 */
    @Field
    private Integer score;

}
