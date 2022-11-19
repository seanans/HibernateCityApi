package com.seanans.HibernateCityApi.Repositories;

import com.seanans.HibernateCityApi.Entities.Person;
import org.springframework.data.repository.CrudRepository;
import java.util.UUID;

public interface PersonRepository extends CrudRepository<Person, UUID> {
}
