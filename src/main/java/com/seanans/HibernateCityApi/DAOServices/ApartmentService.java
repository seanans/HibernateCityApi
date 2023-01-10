package com.seanans.HibernateCityApi.DAOServices;

import com.seanans.HibernateCityApi.DTOs.AddApartmentDto;
import com.seanans.HibernateCityApi.DTOs.ApartmentDto;
import com.seanans.HibernateCityApi.DTOs.PersonDtoHelp;
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
public class ApartmentService {

    @Autowired
    private ApartmentRepository apartmentRepository;

    @Autowired
    private PersonRepository personRepository;


    public List<ApartmentDto> findAll() {
        List<ApartmentDto> apartments = new ArrayList<>();
        Iterable<Apartment> apartmentIterable = apartmentRepository.findAll();
        for (Apartment apartment : apartmentIterable) {
            Set<PersonDtoHelp> personDtoHelps = new HashSet<>();
            if (apartment.getPersons().size() != 0) {
                Iterable<Person> personIterable = apartment.getPersons();
                for (Person person : personIterable) {
                    PersonDtoHelp personDtoHelp = new PersonDtoHelp(
                            person.getId(),
                            person.getName(),
                            person.getSurname()
                    );
                    personDtoHelps.add(personDtoHelp);
                }
                ApartmentDto apartmentDto = new ApartmentDto(
                        apartment.getId(),
                        apartment.getArea(),
                        apartment.getAddress(),
                        personDtoHelps
                );
                apartments.add(apartmentDto);
            } else {
                ApartmentDto apartmentDto = new ApartmentDto(
                        apartment.getId(),
                        apartment.getArea(),
                        apartment.getAddress()
                );
                apartments.add(apartmentDto);
            }
        }
        return apartments;
    }

    public ResponseEntity<HttpStatus> add(AddApartmentDto addApartmentDto) {
        if (addApartmentDto.getPersons() != null) {
            Set<Person> persons = new HashSet<>();
            Iterable<UUID> personUUID = addApartmentDto.getPersons();
            for (UUID uuid : personUUID) {
                if (personRepository.existsById(uuid)) {
                    Optional<Person> optionalPerson = personRepository.findById(uuid);
                    Person person = optionalPerson.get();
                    persons.add(person);
                }
            }
            Apartment apartment = new Apartment(addApartmentDto.getArea(), addApartmentDto.getAddress(), persons);
            apartmentRepository.saveAndFlush(apartment);
            if (apartmentRepository.existsById(apartment.getId())) {
                return new ResponseEntity<>(HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } else {
            Apartment apartment = new Apartment(addApartmentDto.getArea(), addApartmentDto.getAddress());
            apartmentRepository.save(apartment);
            if (apartmentRepository.existsById(apartment.getId())) {
                return new ResponseEntity<>(HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
    }

    public ApartmentDto findById(UUID id) {
        Optional<Apartment> apartmentOptional = apartmentRepository.findById(id);
        Set<Person> persons = apartmentOptional.get().getPersons();
        Set<PersonDtoHelp> personDtoHelps = new HashSet<>();
        for (Person person : persons) {
            PersonDtoHelp personDtoHelp = new PersonDtoHelp(
                    person.getId(),
                    person.getName(),
                    person.getSurname()
            );
            personDtoHelps.add(personDtoHelp);
        }
        return new ApartmentDto(
                apartmentOptional.get().getId(),
                apartmentOptional.get().getArea(),
                apartmentOptional.get().getAddress(),
                personDtoHelps
        );
    }

    public ResponseEntity<HttpStatus> update(UUID id, AddApartmentDto addApartmentDto) {

        if (apartmentRepository.existsById(id)) {
            Apartment localApartment = apartmentRepository.findById(id).get();
            localApartment.setArea(addApartmentDto.getArea());
            localApartment.setAddress(addApartmentDto.getAddress());
            if (addApartmentDto.getPersons() != null) {
                Set<Person> persons = new HashSet<>();
                Iterable<UUID> personUUID = addApartmentDto.getPersons();
                for (UUID uuid : personUUID) {
                    if (personRepository.existsById(uuid)) {
                        Optional<Person> optionalPerson = personRepository.findById(uuid);
                        Person person = optionalPerson.get();
                        persons.add(person);
                    }
                }
                localApartment.setPersons(persons);
            }
            apartmentRepository.save(localApartment);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<HttpStatus> delete(UUID id) {
        if (apartmentRepository.existsById(id)) {
            apartmentRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<HttpStatus> deleteAll(){
        apartmentRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
