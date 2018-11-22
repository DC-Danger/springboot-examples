package com.hz.learnboot.jpa.dao.secondary;

import com.hz.learnboot.jpa.domain.secondary.City;
import org.springframework.data.jpa.repository.JpaRepository;

/** 城市Dao
 * @Author hezhao
 * @Time 2018-07-01 23:03
 */
public interface CityRepository extends JpaRepository<City, Long> {
    City findByCityName(String username);
}
