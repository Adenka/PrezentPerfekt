package com.gusia.backend.person;

import com.gusia.backend.exceptions.ObjectNotFoundException;
import com.gusia.backend.validators.ObjectValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class PersonService {
    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Transactional(readOnly = true)
    public List<Person> getPeople() {
        List<Person> people = new ArrayList<>();
        personRepository.findAll().forEach(person -> {people.add(person);});

        return people;
    }

    @Transactional(readOnly = true)
    public Person getPerson(UUID pid) {
        return personRepository.findById(pid).orElseThrow(
                ObjectNotFoundException::new
        );
    }

    @Transactional
    public void addPerson(Person person) {
        ObjectValidator<Person> validator = new ObjectValidator<>();
        validator.validate(person);
        System.out.println(person);
        personRepository.save(person);
    }

    @Transactional
    public void updatePerson(Person person) {
        ObjectValidator<Person> validator = new ObjectValidator<>();
        validator.validate(person);
        personRepository.save(person);
    }

    @Transactional
    public void removePerson(UUID pid) {
        personRepository.deleteById(pid);
    }
}
