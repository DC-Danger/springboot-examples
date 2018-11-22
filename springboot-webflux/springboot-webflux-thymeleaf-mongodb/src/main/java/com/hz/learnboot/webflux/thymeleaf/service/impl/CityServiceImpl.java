package com.hz.learnboot.webflux.thymeleaf.service.impl;

import com.hz.learnboot.webflux.thymeleaf.dao.CityRepository;
import com.hz.learnboot.webflux.thymeleaf.domain.City;
import com.hz.learnboot.webflux.thymeleaf.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    @Autowired
    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public Flux<City> findAll() {
        return cityRepository.findAll();
    }

    @Override
    public Mono<City> insertByCity(City city) {
        return cityRepository.save(city);
    }

    @Override
    public Mono<City> update(City city) {
        return cityRepository.save(city);
    }

    @Override
    public Mono<Void> delete(Long id) {
        return cityRepository.deleteById(id);
    }

    @Override
    public Mono<City> findById(Long id) {
        return cityRepository.findById(id);
    }
}
