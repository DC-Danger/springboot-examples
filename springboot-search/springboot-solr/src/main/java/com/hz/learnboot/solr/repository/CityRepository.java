package com.hz.learnboot.solr.repository;

import com.hz.learnboot.solr.domain.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.repository.SolrCrudRepository;

import java.util.List;

/**
 * 使用 Solr JPA 简化开发
 *
 * @Author hezhao
 * @Time 2018-07-22 22:33
 */
public interface CityRepository extends SolrCrudRepository<City, Long> {

    // 如果要与名字精确匹配，则如下写法，其中Name为具体的属性名称，
    // 如果包含查询，模糊查询则findByCityNameContaining即可。写法与jpa写法一致。也要注意下solrHome中的manage-schema文件对属性类型的配置是text_ik还是string类型。
    List<City> findByCityName(String name);

    // 可以把@Query注释掉findByDescriptionContaining就变成了 name:*?0*，仅按名称匹配
    // @Query(value = "name:*?0* or category:*?0*")
    Page<City> findByDescriptionContaining(String name, Pageable pageable);

}
