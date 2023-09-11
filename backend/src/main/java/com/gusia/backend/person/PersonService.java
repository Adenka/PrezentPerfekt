package com.gusia.backend.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
        Optional<Person> personOptional = personRepository.findById(pid);
        //TODO - exception?
        return personOptional.orElse(null);

    }

    @Transactional
    public void addPerson(Person person) {
        System.out.println(person);
        personRepository.save(person);
    }

    @Transactional
    public void updatePerson(Person person) {
        personRepository.save(person);
    }

    @Transactional
    public void removePerson(UUID pid) {
        personRepository.deleteById(pid);
    }
}
