package com.seanans.HibernateCityApi.DAOServices;

import com.seanans.HibernateCityApi.Entities.Apartment;
import com.seanans.HibernateCityApi.Repositories.ApartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ApartmentService {

    @Autowired
    private ApartmentRepository apartmentRepository;

    public ApartmentService(ApartmentRepository apartmentRepository) {
        this.apartmentRepository = apartmentRepository;
    }

    public List<Apartment> findAll() {
        List<Apartment> apartments = new ArrayList<>();
        Iterable<Apartment> apartmentIterable = apartmentRepository.findAll();
        for (Apartment apartment : apartmentIterable) {
            apartments.add(apartment);
        }
        return apartments;
    }

    public ResponseEntity add(Apartment apartment) {
        apartmentRepository.save(apartment);
        if(apartmentRepository.existsById(apartment.getId())) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public Optional<Apartment> findById(UUID id) {
        return apartmentRepository.findById(id);
    }

    public ResponseEntity update(UUID id, Apartment apartment) {

        if (apartmentRepository.existsById(id)) {
            Apartment localApartment = apartmentRepository.findById(id).get();
            localApartment.setArea(apartment.getArea());
            localApartment.setAddress(apartment.getAddress());
            apartmentRepository.save(localApartment);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity delete(UUID id) {
        if (apartmentRepository.existsById(id)) {
            apartmentRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
