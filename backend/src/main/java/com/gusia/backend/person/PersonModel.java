package com.gusia.backend.person;

import org.springframework.hateoas.RepresentationModel;

import java.util.UUID;

public class PersonModel extends RepresentationModel<PersonModel> {
    //TODO - wywalić to, po prostu nie wiem jak front rozegrać
    private final UUID pid;
    private final String name;

    public PersonModel(Person person) {
        this.pid = person.getPid();
        this.name = person.getName();
    }

    public UUID getPid() {
        return pid;
    }

    public String getName() {
        return name;
    }
}
