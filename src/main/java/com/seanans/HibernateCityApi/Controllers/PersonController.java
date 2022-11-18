package com.seanans.HibernateCityApi.Controllers;

import com.seanans.HibernateCityApi.DAOServices.PersonService;
import com.seanans.HibernateCityApi.Entities.Person;
import com.seanans.HibernateCityApi.Repositories.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@Slf4j
@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
@RequestMapping("/persons")
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonService personService;

    @GetMapping("")
    @ResponseBody
    public List<Person> getAll() {
        return personService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Person findById(@PathVariable("id") UUID uuid) {
        return personService.findById(uuid);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity insert(@RequestBody() Person person) {
        log.info("New Person name: {}, surname: {}", person.getName(), person.getSurname());
        return personService.add(person);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity update(@PathVariable("id") UUID uuid,@RequestBody() Person person) {
        log.info("Update Person:{} to name: {}, surname: {}", uuid, person.getName(), person.getSurname());
        return personService.update(uuid, person);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity delete(@PathVariable("id") UUID uuid) {
        return personService.delete(uuid);
    }
}
