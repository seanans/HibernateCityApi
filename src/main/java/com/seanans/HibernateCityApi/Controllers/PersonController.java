package com.seanans.HibernateCityApi.Controllers;

import com.seanans.HibernateCityApi.DAOServices.PersonService;
import com.seanans.HibernateCityApi.DTOs.PersonDto;
import com.seanans.HibernateCityApi.Entities.Person;
import com.seanans.HibernateCityApi.DTOs.AddPersonDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public List<PersonDto> getAll() {
        log.info("Get All Persons");
        return personService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public PersonDto findById(@PathVariable("id") UUID id) {
        log.info("Get Person: {}", id);
        return personService.findById(id);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity insert(@RequestBody() AddPersonDto person) {
        log.info("New Person name: {}, surname: {}, apartments: {}", person.getName(), person.getSurname(), person.getApartments());
        return personService.add(person);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity update(@PathVariable("id") UUID id, @RequestBody() AddPersonDto person) {
        log.info("Update Person: {} to name: {}, surname: {}, apartments: {}", id, person.getName(), person.getSurname(), person.getApartments());
        return personService.update(id, person);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity delete(@PathVariable("id") UUID id) {
        log.info("Delete Person: {}", id);
        return personService.delete(id);
    }

    @DeleteMapping("/bind/{personId}&{apartmentId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity deleteBind(@PathVariable("personId") UUID personId, @PathVariable("apartmentId") UUID apartmentId) {
        log.info("Delete Person`s: {},  Bind: {}", personId, apartmentId);
        return personService.deleteBind(personId, apartmentId);
    }

    @DeleteMapping("/all")
    public void deleteAll() {
        log.info("Delete All");
        personService.deleteAll();
    }
}
