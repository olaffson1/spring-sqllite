package com.mazur.o2r.person;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("persons")
public class PersonController {
    private final PersonRepository personRepository;

    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping(path = "", produces = "application/json")
    ResponseEntity<List<Person>> findAll() {
        log.info("Responding with all persons");
        return ResponseEntity.ok(personRepository.findAll());
    }

    @GetMapping(path = "actives", produces = "application/json")
    ResponseEntity<List<Person>> actives() {
        log.info("Responding with all active persons");
        return ResponseEntity.ok(personRepository.findAllByActiveIsTrue());
    }

    @DeleteMapping("{id}")
    ResponseEntity delete(@PathVariable Integer id) {
        log.info("Requested delete user #{}", id);

        Optional<Person> person = personRepository.findById(id);

        log.debug("Deleting person {}", person);

        if (person.isPresent()) {
            personRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            log.error("Not found user");
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping
    ResponseEntity save(@RequestBody PersonSaveDao personDao) {
        log.info("Creating new user: {}", personDao);

        Person person = new Person();
        person.setName(personDao.getName());
        person.setMessage(personDao.getMessage());
        person.setActive(true);

        personRepository.save(person);

        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
