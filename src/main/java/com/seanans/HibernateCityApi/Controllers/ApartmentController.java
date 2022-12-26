package com.seanans.HibernateCityApi.Controllers;

import com.seanans.HibernateCityApi.DAOServices.ApartmentService;
import com.seanans.HibernateCityApi.DTOs.AddApartmentDto;
import com.seanans.HibernateCityApi.DTOs.ApartmentDto;
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
@ResponseStatus(HttpStatus.OK)
@RequestMapping("/apartments")
public class ApartmentController {


    @Autowired
    private ApartmentService apartmentService;

    public ApartmentController(ApartmentService apartmentService) {
        this.apartmentService = apartmentService;
    }

    @GetMapping("")
    @ResponseBody
    public List<ApartmentDto> getAll() {
        log.info("Get All Apartments");
        return apartmentService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public ApartmentDto findById(@PathVariable("id") UUID id) {
        log.info("Get Apartment: {}", id);
        return apartmentService.findById(id);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity insert(@RequestBody() AddApartmentDto apartment) {
        log.info("New Apartments address: {} , area: {}, persons: {}", apartment.getAddress(), apartment.getArea(), apartment.getPersons());
        return apartmentService.add(apartment);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity update(@PathVariable("id") UUID id, @RequestBody() AddApartmentDto apartment) {
        log.info("Update Apartment: {} to Address: {}, Area: {}", id, apartment.getAddress(), apartment.getArea());
        return apartmentService.update(id, apartment);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity delete(@PathVariable("id") UUID id) {
        log.info("Delete Apartment: {}", id);
        return apartmentService.delete(id);
    }

    @DeleteMapping("/all")
    public void deleteAll() {
        log.info("Delete all");
        apartmentService.deleteAll();
    }
}
