package com.gusia.backend.person;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;
import java.util.List;

public interface PersonRepository extends CrudRepository<Person, UUID> {
    List<Person> findByUserId(Integer id);
}
