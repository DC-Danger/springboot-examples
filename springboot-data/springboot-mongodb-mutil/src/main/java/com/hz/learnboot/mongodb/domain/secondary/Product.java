package com.hz.learnboot.mongodb.domain.secondary;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 商品信息
 *
 * Created by hezhao on 2018-07-05 19:23
 */
@Data @Builder @NoArgsConstructor @AllArgsConstructor
@Document(collection = "product")
public class Product {
    @Id
    private String id;

    private String name;

}
