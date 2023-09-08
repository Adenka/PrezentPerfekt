package com.gusia.backend.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import java.util.List;

@Service
public class HomeService {
    private final HomeRepository homeRepository;

    @Autowired
    public HomeService(HomeRepository homeRepository) {
        this.homeRepository = homeRepository;
    }

    public List<Person> getPeople() {
        List<Person> people = new ArrayList<>();
        homeRepository.findAll().forEach(person -> {people.add(person);});

        return people;
    }

    public void addPerson(Person person) {
        homeRepository.save(person);
    }

    public void updatePerson(Person person) {
        homeRepository.save(person);
    }

    public void removePerson(int pid) {
        homeRepository.deleteById(pid);
    }
}
