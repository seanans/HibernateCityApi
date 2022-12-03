package com.seanans.HibernateCityApi.DAOServices;

import com.seanans.HibernateCityApi.DTOs.AddPersonDto;
import com.seanans.HibernateCityApi.DTOs.ApartmentDtoHelp;
import com.seanans.HibernateCityApi.DTOs.PersonDto;
import com.seanans.HibernateCityApi.Entities.Apartment;
import com.seanans.HibernateCityApi.Entities.Person;
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

    public List<PersonDto> findAll() {
        List<PersonDto> people = new ArrayList<>();
        Iterable<Person> personIterable = personRepository.findAll();
        for (Person person : personIterable) {
            Set<ApartmentDtoHelp> apartmentDtoHelps = new HashSet<>();
            if (person.getApartments().size() != 0) {
                Iterable<Apartment> apartmentIterable = person.getApartments();
                for (Apartment apartment : apartmentIterable) {
                    ApartmentDtoHelp apartmentDtoHelp = new ApartmentDtoHelp(
                            apartment.getId(),
                            apartment.getArea(),
                            apartment.getAddress()
                    );
                    apartmentDtoHelps.add(apartmentDtoHelp);
                }
                PersonDto personDto = new PersonDto(
                        person.getId(),
                        person.getName(),
                        person.getSurname(),
                        apartmentDtoHelps
                );
                people.add(personDto);
            } else {
                PersonDto personDto = new PersonDto(
                        person.getId(),
                        person.getName(),
                        person.getSurname()
                );
                people.add(personDto);
            }
        }
        return people;
    }

    public ResponseEntity add(AddPersonDto addPersonDto) {
        if (addPersonDto.getApartments() != null) {
            Set<Apartment> apartments = new HashSet<>();
            Iterable<UUID> apartmentUUID = addPersonDto.getApartments();
            for (UUID uuid : apartmentUUID) {
                if (apartmentRepository.existsById(uuid)) {
                    Optional<Apartment> optionalApartment = apartmentRepository.findById(uuid);
                    Apartment apartment = optionalApartment.get();
                    apartments.add(apartment);
                }
            }
            Person person = new Person(addPersonDto.getName(), addPersonDto.getSurname(), apartments);
            personRepository.save(person);
            if (personRepository.existsById(person.getId())) {
                return new ResponseEntity<>(HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } else {
            Person person = new Person(addPersonDto.getName(), addPersonDto.getSurname());
            personRepository.save(person);
            if (personRepository.existsById(person.getId())) {
                return new ResponseEntity<>(HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }

    }

    public PersonDto findById(UUID id) {
        Optional<Person> personOptional = personRepository.findById(id);
        Set<Apartment> apartments = personOptional.get().getApartments();
        Set<ApartmentDtoHelp> apartmentDtoHelps = new HashSet<>();
        for (Apartment apartment : apartments) {
            ApartmentDtoHelp apartmentDtoHelp = new ApartmentDtoHelp(
                    apartment.getId(),
                    apartment.getArea(),
                    apartment.getAddress()
            );
            apartmentDtoHelps.add(apartmentDtoHelp);
        }
        return new PersonDto(
                personOptional.get().getId(),
                personOptional.get().getName(),
                personOptional.get().getSurname(),
                apartmentDtoHelps
        );
    }

    public ResponseEntity update(UUID id, AddPersonDto addPersonDto) {

        if (personRepository.existsById(id)) {
            Person localPerson = personRepository.findById(id).get();
            localPerson.setName(addPersonDto.getName());
            localPerson.setSurname(addPersonDto.getSurname());
            if (addPersonDto.getApartments() != null) {
                Set<Apartment> apartments = new HashSet<>();
                Iterable<UUID> apartmentUUID = addPersonDto.getApartments();
                for (UUID uuid : apartmentUUID) {
                    if (apartmentRepository.existsById(uuid)) {
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
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity deleteBind(UUID personId, UUID apartmentId) {
        if (personRepository.existsById(personId)) {
            Person person = personRepository.findById(personId).get();
            Set<Apartment> apartments = person.getApartments();
            Set<Apartment> apartmentSet = new HashSet<>();
            for (Apartment apartment : apartments) {
                if (!apartment.getId().equals(apartmentId)) {
                    apartmentSet.add(apartment);
                }
            }
            person.setApartments(apartmentSet);
            personRepository.save(person);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public void deleteAll() {
       personRepository.deleteAll();
    }
}
