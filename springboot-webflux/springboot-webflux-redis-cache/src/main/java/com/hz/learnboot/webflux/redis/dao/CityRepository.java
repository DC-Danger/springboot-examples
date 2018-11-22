package com.hz.learnboot.webflux.redis.dao;

import com.hz.learnboot.webflux.redis.domain.City;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends ReactiveMongoRepository<City, Long> {

}
