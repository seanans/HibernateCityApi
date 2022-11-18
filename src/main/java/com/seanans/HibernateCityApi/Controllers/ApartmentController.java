package com.seanans.HibernateCityApi.Controllers;

import com.seanans.HibernateCityApi.Entities.Apartment;
import com.seanans.HibernateCityApi.Repositories.ApartmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/apartments")
public class ApartmentController {

    @Autowired
    private ApartmentRepository apartmentRepository;

    @GetMapping("")
    public List<Apartment> getAll() {
        return null;
    }
}
