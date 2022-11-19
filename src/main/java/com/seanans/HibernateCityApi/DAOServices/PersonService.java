package com.seanans.HibernateCityApi.DAOServices;

import com.seanans.HibernateCityApi.Entities.Person;
import com.seanans.HibernateCityApi.Repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> findAll() {
        List<Person> people = new ArrayList<>();
        Iterable<Person> personIterable = personRepository.findAll();
        for (Person person: personIterable) {
            people.add(person);
        }
        return people;
    }

    public ResponseEntity add(Person person) {
        personRepository.save(person);
        if (personRepository.existsById(person.getId())) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    public Optional<Person> findById(UUID id) {
        return personRepository.findById(id);
    }

    public ResponseEntity update(UUID id, Person person) {

        if (personRepository.existsById(id)){
            Person localPerson = personRepository.findById(id).get();
            localPerson.setName(person.getName());
            localPerson.setSurname(person.getSurname());
            personRepository.save(localPerson);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    public ResponseEntity delete(UUID id) {
        if (personRepository.existsById(id)) {
            personRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
