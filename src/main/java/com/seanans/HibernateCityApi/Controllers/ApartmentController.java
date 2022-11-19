package com.seanans.HibernateCityApi.Controllers;

import com.seanans.HibernateCityApi.DAOServices.ApartmentService;
import com.seanans.HibernateCityApi.Entities.Apartment;
import com.seanans.HibernateCityApi.Repositories.ApartmentRepository;
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
@RequestMapping("/apartments")
public class ApartmentController {


    @Autowired
    private ApartmentService apartmentService;

    @GetMapping("")
    @ResponseBody
    public List<Apartment> getAll() {
        log.info("Get All Apartments");
        return apartmentService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Optional<Apartment> findById(@PathVariable("id") UUID id) {
        log.info("Get Apartment: {}", id);
        return apartmentService.findById(id);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity insert(@RequestBody() Apartment apartment) {
        log.info("New Apartments address: {} , area: {}", apartment.getAddress(), apartment.getArea());
        return apartmentService.add(apartment);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity update(@PathVariable("id") UUID id, @RequestBody() Apartment apartment) {
        log.info("Update Apartment: {} to Address: {}, Area: {}", id, apartment.getAddress(), apartment.getArea());
        return apartmentService.update(id, apartment);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity delete(@PathVariable("id") UUID id) {
        log.info("Delete Apartment: {}", id);
        return apartmentService.delete(id);
    }

}
