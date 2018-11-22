package com.hz.learnboot.jpa.domain.secondary;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/** 城市信息
 * @Author hezhao
 * @Time 2018-06-30 23:53
 */
@Data @Builder @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "city")
public class City {
    /** 城市编号 */
    @Id
    @Column(name= "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 省份编号 */
    @Column(name= "province_id", nullable = false)
    private Long provinceId;

    /** 城市名称 */
    @Column(name= "city_name", nullable = false)
    private String cityName;

    /** 描述 */
    @Column(name= "description")
    private String description;

}
