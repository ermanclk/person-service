package com.embl.person.repository;

import com.embl.person.entity.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person,Long> {

    @Query("SELECT p FROM Person p WHERE (:name is null or p.firstName = :name) and (:lastName is null"
            + " or p.lastName = :lastName) and  (:age is null or p.age = :age)")
    Page<Person> findAllByNameAndAgeAndJob(String name, String lastName, Integer age, Pageable paging);
}
