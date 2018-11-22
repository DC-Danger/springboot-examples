package com.hz.learnboot.cache.controller;

import com.hz.learnboot.cache.service.jsr107.Person;
import com.hz.learnboot.cache.service.jsr107.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试路径：http://localhost:8090/person/987654321
 * 多刷新自己就会发现从缓存中拿数据了
 *
 * Created by hezhao on 2018-07-06 16:58
 */
@RestController
@RequestMapping(value = "/person")
public class PersonController {
    @Autowired
    PersonService personService;

    @GetMapping("/{ssn}")
    public String getPerson(@PathVariable("ssn") int ssn) {
        return personService.getPerson(ssn).toString();
    }

    @GetMapping("/save/{ssn}/{firstName}/{lastName}")
    public void save(@PathVariable("ssn") int ssn, @PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName) {
        Person person = Person.builder().firstName(firstName).lastName(lastName).build();
        personService.savePerson(ssn, person);
    }

    @GetMapping("/delete/{ssn}")
    public void delete(@PathVariable("ssn") int ssn) {
        personService.removePerson(ssn);
    }
}
