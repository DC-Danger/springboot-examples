package com.hz.learnboot.mybatis.dao.cluster;

import com.hz.learnboot.mybatis.domain.cluster.City;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/** 城市 DAO 接口类
 * @Author hezhao
 * @Time 2018-06-30 23:59
 */
public interface CityMapper {

    /**
     * 查询所有城市信息
     * @return
     */
    List<City> queryAll();

    /**
     * 根据城市名称，查询城市信息
     *
     * @param cityName 城市名
     */
    City findByName(@Param("cityName") String cityName);
}
