package com.seanans.HibernateCityApi.Controllers;

import com.seanans.HibernateCityApi.DAOServices.PersonService;
import com.seanans.HibernateCityApi.Entities.Person;
import com.seanans.HibernateCityApi.Repositories.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@Slf4j
@ResponseStatus(HttpStatus.OK)
@RequestMapping("/persons")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping("")
    @ResponseBody
    public List<Person> getAll() {
        log.info("Get All Persons");
        return personService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Optional<Person> findById(@PathVariable("id") UUID id) {
        log.info("Get Person: {}", id);
        return personService.findById(id);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity insert(@RequestBody() Person person) {
        log.info("New Person name: {}, surname: {}", person.getName(), person.getSurname());
        return personService.add(person);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity update(@PathVariable("id") UUID id,@RequestBody() Person person) {
        log.info("Update Person: {} to name: {}, surname: {}", id, person.getName(), person.getSurname());
        return personService.update(id, person);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity delete(@PathVariable("id") UUID id) {
        log.info("Delete Person: {}", id);
        return personService.delete(id);
    }

}
