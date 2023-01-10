package com.seanans.HibernateCityApi.Repositories;

import com.seanans.HibernateCityApi.Entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonRepository extends JpaRepository<Person, UUID> {

    @Query(value = "SELECT * FROM public.person WHERE name = ?", nativeQuery = true)
    List<Person> findByName(String name);
    Optional<Person> findBySurname(String surname);

}
