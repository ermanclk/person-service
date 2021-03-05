package com.embl.person.service.impl;

import com.embl.person.controller.PersonController;
import com.embl.person.entity.Person;
import com.embl.person.exception.NotFoundException;
import com.embl.person.repository.PersonRepository;
import com.embl.person.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonServiceImp implements PersonService {

    Logger logger = LoggerFactory.getLogger(PersonController.class);
    private PersonRepository personRepository;

    public PersonServiceImp(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public Person create(Person person) {
        logger.info("creating person");
        return personRepository.save(person);
    }

    @Override
    public Person update(Person person) {
        logger.info("update person");
        if (person.getId() == null) {
            throw new IllegalArgumentException("Id parameter cannot be null for update operation");
        }
        Person existingPerson = personRepository.findById(person.getId())
                .orElseThrow(() -> new NotFoundException("cannot find element with id:" + person.getId()));

        BeanUtils.copyProperties(person,existingPerson);
        return personRepository.save(existingPerson);
    }

    @Override
    public Person findById(long id) {
        logger.info("finding person..");
        return personRepository.findById(id).orElseThrow(() -> new NotFoundException("element not found - " + id));
    }

    @Override
    public Page<Person> findAll(Optional<String> name, Optional<String> lastName, Optional<Integer> age, PageRequest paging) {
        logger.info("finding  persons");
        return personRepository.findAllByNameAndAgeAndJob(
                name.orElse(null),
                lastName.orElse(null),
                age.orElse(null), paging);
    }

}
