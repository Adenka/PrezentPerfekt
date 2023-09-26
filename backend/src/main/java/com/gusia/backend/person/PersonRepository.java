package com.gusia.backend.person;

import com.gusia.backend.exceptions.ObjectNotFoundException;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;
import java.util.List;

public interface PersonRepository extends CrudRepository<Person, UUID> {
    List<Person> findByUserId(Integer id);

    default Person findPerson(UUID pid) {
        return findById(pid).orElseThrow(ObjectNotFoundException::new);
    }
}
