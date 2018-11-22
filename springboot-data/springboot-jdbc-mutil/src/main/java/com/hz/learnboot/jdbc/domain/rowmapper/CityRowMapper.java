package com.hz.learnboot.jdbc.domain.rowmapper;

import com.hz.learnboot.jdbc.domain.City;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/** 自定义RowMapper， 用于表数据与实体类的转换
 * Created by hezhao on 2018-07-04 09:41
 */
public class CityRowMapper implements RowMapper<City> {

    @Override
    public City mapRow(ResultSet rs, int index) throws SQLException {
        City city = new City();
        city.setId(rs.getLong("id"));
        city.setProvinceId(rs.getLong("province_id"));
        city.setCityName(rs.getString("city_name"));
        city.setDescription(rs.getString("description"));
        return city;
    }

}
