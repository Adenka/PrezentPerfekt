package com.gusia.backend.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> getPeople() {
        List<Person> people = new ArrayList<>();
        personRepository.findAll().forEach(person -> {people.add(person);});

        return people;
    }

    public Person getPerson(int pid) {
        Optional<Person> personOptional = personRepository.findById(pid);
        //TODO - exception?
        return personOptional.orElse(null);

    }

    public void addPerson(Person person) {
        personRepository.save(person);
    }

    public void updatePerson(Person person) {
        personRepository.save(person);
    }

    public void removePerson(int pid) {
        personRepository.deleteById(pid);
    }
}
