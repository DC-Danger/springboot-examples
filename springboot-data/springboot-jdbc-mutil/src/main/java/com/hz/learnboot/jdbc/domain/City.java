package com.hz.learnboot.jdbc.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 城市信息
 * @Author hezhao
 * @Time 2018-06-30 23:53
 */
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class City {
    /** 城市编号 */
    private Long id;

    /** 省份编号 */
    private Long provinceId;

    /** 城市名称 */
    private String cityName;

    /** 描述 */
    private String description;

}
