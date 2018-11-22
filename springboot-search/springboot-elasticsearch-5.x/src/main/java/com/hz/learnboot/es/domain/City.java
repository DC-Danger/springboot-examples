package com.hz.learnboot.es.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

/**
 * 城市信息
 *
 * @Author hezhao
 * @Time 2018-06-30 23:53
 */
@Data @Builder @NoArgsConstructor @AllArgsConstructor
@Document(indexName = "province", type = "city", shards = 1,replicas = 0, refreshInterval = "-1")
public class City implements Serializable {
    private static final long serialVersionUID = -3322738983942005670L;

    /** 城市编号 */
    @Id
    private Long id;

    /** 省份编号 */
    @Field(type = FieldType.Long)
    private Long provinceId;

    /**
     * 城市名称，不需要分词，可以搜索
     */
    @Field(type = FieldType.Text, index = false)
    private String cityName;

    /**
     * 描述，可以通过ik 分词器进行分词
     */
    @Field(type = FieldType.Text, searchAnalyzer = "ik", analyzer = "ik")
    private String description;

    /** 城市评分 */
    @Field
    private Integer score;

}
