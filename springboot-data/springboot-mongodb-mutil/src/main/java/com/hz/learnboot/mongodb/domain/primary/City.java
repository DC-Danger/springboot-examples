package com.hz.learnboot.mongodb.domain.primary;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/** 城市信息
 * @Author hezhao
 * @Time 2018-06-30 23:53
 */
@Data @Builder @NoArgsConstructor @AllArgsConstructor
@Document(collection = "city")
public class City implements Serializable {
    private static final long serialVersionUID = -7717158340585419423L;

    /** 城市编号 */
    @Id
    private Long id;

    /** 省份编号 */
    private Long provinceId;

    /** 城市名称 */
    private String cityName;

    /** 描述 */
    private String description;

}
