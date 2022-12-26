package com.seanans.HibernateCityApi.Repositories;

import com.seanans.HibernateCityApi.Entities.Apartment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface ApartmentRepository extends JpaRepository<Apartment, UUID> {
}
