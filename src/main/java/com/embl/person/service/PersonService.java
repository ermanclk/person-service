package com.embl.person.service;

import com.embl.person.entity.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;

public interface PersonService {

    Person create(Person person);

    Person update(Person person);

    Person findById(long id);

    Page<Person> findAll(Optional<String> name, Optional<String> job, Optional<Integer> age, PageRequest of);

    void deleteById(long parseLong);
}
