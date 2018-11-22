package com.hz.learnboot.webflux.thymeleaf.dao;

import com.hz.learnboot.webflux.thymeleaf.domain.City;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends ReactiveMongoRepository<City, Long> {

}
