package com.seanans.HibernateCityApi.DAOServices;

import com.seanans.HibernateCityApi.Entities.Person;
import com.seanans.HibernateCityApi.Repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@Repository
public class PersonService {
    @Autowired
    PersonRepository personRepository;

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
        if (personRepository.existsById(person.getUuid())) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    public Person findById(UUID uuid) {
        List<Person> personList = personRepository.findById(uuid).stream().toList();
        if(!personList.isEmpty()) {
            Person person = new Person(
                    personList.get(0).getName(),
                    personList.get(0).getSurname()
            );
            return person;
        } else {
            return null;
        }

    }

    public ResponseEntity update(UUID uuid, Person person) {
        if (personRepository.existsById(uuid)){
            Person localperson = personRepository.findById(uuid).stream().toList().get(0);
            localperson.setName(person.getName());
            localperson.setSurname(person.getSurname());
            personRepository.save(localperson);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
