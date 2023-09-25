package com.gusia.backend.person;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

public class PersonModelAssembler extends RepresentationModelAssemblerSupport<Person, PersonModel> {
    public PersonModelAssembler() {
        super(PersonController.class, PersonModel.class);
    }

    @Override
    // Ta metoda wywołuje instantiateModel pod pokrywką
    public PersonModel toModel(Person person) {
        return createModelWithId(person.getPid(), person);
    }

    @Override
    protected PersonModel instantiateModel(Person person) {
        return new PersonModel(person);
    }
}
