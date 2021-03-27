package com.mazur.o2r.person;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

interface PersonRepository extends CrudRepository<Person, Integer> {
    List<Person> findAll();

    List<Person> findAllByActiveIsTrue();
}
