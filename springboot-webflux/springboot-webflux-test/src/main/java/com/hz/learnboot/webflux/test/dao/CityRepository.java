package com.hz.learnboot.webflux.test.dao;

import com.hz.learnboot.webflux.test.domain.City;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends ReactiveMongoRepository<City, Long> {

}
