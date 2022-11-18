package com.seanans.HibernateCityApi.Repositories;

import com.seanans.HibernateCityApi.Entities.Apartment;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ApartmentRepository extends CrudRepository<Apartment, UUID> {
}
