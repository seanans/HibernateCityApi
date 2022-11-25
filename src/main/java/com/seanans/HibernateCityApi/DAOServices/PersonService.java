package com.seanans.HibernateCityApi.DAOServices;

import com.seanans.HibernateCityApi.Entities.Apartment;
import com.seanans.HibernateCityApi.Entities.Person;
import com.seanans.HibernateCityApi.Entities.TPerson;
import com.seanans.HibernateCityApi.Repositories.ApartmentRepository;
import com.seanans.HibernateCityApi.Repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    ApartmentRepository apartmentRepository;

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

    public ResponseEntity add(TPerson tPerson) {
       if (!tPerson.getApartments().isEmpty()) {
           Set<Apartment> apartments = new HashSet<>();
           Iterable<UUID> apartmentUUID = tPerson.getApartments();
           for (UUID uuid: apartmentUUID) {
               if(apartmentRepository.existsById(uuid)){
                   Optional<Apartment> optionalApartment = apartmentRepository.findById(uuid);
                   Apartment apartment = optionalApartment.get();
                   apartments.add(apartment);
               }
           }
           Person person = new Person(tPerson.getName(), tPerson.getSurname(), apartments);
           personRepository.save(person);
           if (personRepository.existsById(person.getId())) {
               return new ResponseEntity<>(HttpStatus.CREATED);
           } else {
               return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
           }
       } else {
           Person person = new Person(tPerson.getName(), tPerson.getSurname());
           personRepository.save(person);
           if (personRepository.existsById(tPerson.getId())) {
               return new ResponseEntity<>(HttpStatus.CREATED);
           } else {
               return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
           }
       }

    }

    public Optional<Person> findById(UUID id) {
        return personRepository.findById(id);
    }

    public ResponseEntity update(UUID id, TPerson tPerson) {

        if (personRepository.existsById(id)){
            Person localPerson = personRepository.findById(id).get();
            localPerson.setName(tPerson.getName());
            localPerson.setSurname(tPerson.getSurname());
            if (!tPerson.getApartments().isEmpty()) {
                Set<Apartment> apartments = new HashSet<>();
                Iterable<UUID> apartmentUUID = tPerson.getApartments();
                for (UUID uuid: apartmentUUID) {
                    if(apartmentRepository.existsById(uuid)){
                        Optional<Apartment> optionalApartment = apartmentRepository.findById(uuid);
                        Apartment apartment = optionalApartment.get();
                        apartments.add(apartment);
                    }
                }
                localPerson.setApartments(apartments);
            }
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
