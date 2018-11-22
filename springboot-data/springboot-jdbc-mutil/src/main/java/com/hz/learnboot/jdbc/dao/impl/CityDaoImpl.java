package com.hz.learnboot.jdbc.dao.impl;

import com.hz.learnboot.jdbc.dao.CityDao;
import com.hz.learnboot.jdbc.domain.City;
import com.hz.learnboot.jdbc.domain.rowmapper.CityRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by hezhao on 2018-07-04 09:36
 */
@Repository
public class CityDaoImpl implements CityDao {

    // 指定jdbcTemplate
    @Resource(name = "secondaryJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<City> queryAll() {
        String sql = "select * from city";
        return jdbcTemplate.query(sql, new CityRowMapper());
    }

    @Override
    public int insert(City city) {
        String sql = "INSERT INTO city VALUES (?,?,?,?)";
        return jdbcTemplate.update(sql, city.getId(), city.getProvinceId(), city.getCityName(), city.getDescription());
    }
}
