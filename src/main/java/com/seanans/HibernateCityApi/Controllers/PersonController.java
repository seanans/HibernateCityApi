package com.seanans.HibernateCityApi.Controllers;

import com.seanans.HibernateCityApi.DAOServices.PersonService;
import com.seanans.HibernateCityApi.DTOs.AddPersonDto;
import com.seanans.HibernateCityApi.DTOs.PersonDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@Slf4j
@ResponseStatus(HttpStatus.OK)
@RequestMapping(value = "/persons", produces = MediaType.APPLICATION_JSON_VALUE)
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


    @GetMapping("/name")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<PersonDto> findByName(@RequestParam(value="name") String name) {
        log.info("Get Persons by name: {}", name);
        return personService.findByName(name);
    }

    @PostMapping("")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<HttpStatus> insert(@RequestBody() AddPersonDto person) {
        log.info("New Person name: {}, surname: {}, apartments: {}", person.getName(), person.getSurname(), person.getApartments());
        return personService.add(person);
    }

    @PutMapping("/{id}")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<HttpStatus> update(@PathVariable("id") UUID id, @RequestBody() AddPersonDto person) {
        log.info("Update Person: {} to name: {}, surname: {}, apartments: {}", id, person.getName(), person.getSurname(), person.getApartments());
        return personService.update(id, person);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") UUID id) {
        log.info("Delete Person: {}", id);
        return personService.delete(id);
    }

    @DeleteMapping("/bind/{personId}&{apartmentId}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<HttpStatus> deleteBind(@PathVariable("personId") UUID personId, @PathVariable("apartmentId") UUID apartmentId) {
        log.info("Delete Person`s: {},  Bind: {}", personId, apartmentId);
        return personService.deleteBind(personId, apartmentId);
    }

    @DeleteMapping("/all")
    @ResponseBody
    public ResponseEntity<HttpStatus> deleteAll() {
        log.info("Delete All");
        return personService.deleteAll();
    }
}
