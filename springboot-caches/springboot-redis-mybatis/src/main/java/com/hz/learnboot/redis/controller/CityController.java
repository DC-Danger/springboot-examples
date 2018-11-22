package com.hz.learnboot.redis.controller;

import com.hz.learnboot.redis.domain.City;
import com.hz.learnboot.redis.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by bysocket on 07/02/2017.
 */
@RestController
@RequestMapping("/city")
public class CityController {

    @Autowired
    private CityService cityService;

    @GetMapping(value = {"", "/list"})
    public List<City> findAllCity() {
        return cityService.findAllCity();
    }

    @GetMapping("/{id}")
    public City findOneCity(@PathVariable("id") Long id) {
        return cityService.findCityById(id);
    }

    @PostMapping("/save")
    public void createCity(@RequestBody City city) {
        cityService.saveCity(city);
    }

    @PutMapping("/save")
    public void modifyCity(@RequestBody City city) {
        cityService.updateCity(city);
    }

    @DeleteMapping("/{id}")
    public void modifyCity(@PathVariable("id") Long id) {
        cityService.deleteCity(id);
    }
}
