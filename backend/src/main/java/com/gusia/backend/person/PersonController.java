package com.gusia.backend.person;

import com.gusia.backend.user.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

// na początku Spring przejrzy mój epicki kod i znajdzie te adnotacje,
// więc nie trzeba się pocić, żeby coś gdzieś pododawać

// kontroler do jakiejś ścieżki
@RestController
@RequestMapping(path="/api/people")
//TODO - można dorzucić produces="application/json do @RequestMapping (strona 142)
public class PersonController {
    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public CollectionModel<PersonModel> getPeople(@AuthenticationPrincipal AppUser user) {
        List<Person> peopleList = personService.getPeople(user);
        CollectionModel<PersonModel> personModels = new PersonModelAssembler().toCollectionModel(peopleList);

        personModels.add(
                linkTo(methodOn(PersonController.class).getPeople(user)).withSelfRel()
        );

        return personModels;
    }

    @GetMapping("/{pid}")
    public PersonModel getPerson(@PathVariable("pid") UUID pid,
                            @AuthenticationPrincipal AppUser user) {
        Person person = personService.getPerson(pid, user);
        return new PersonModelAssembler().toModel(person);
    }

    @PostMapping
                                 // request payload będzie reprezentacja JSON tego obiektu
    public PersonModel addPerson(@RequestBody Person person,
                            @AuthenticationPrincipal AppUser user) {
        Person personAdded = personService.addPerson(person, user);
        return new PersonModelAssembler().toModel(personAdded);
    }

    @PutMapping("/{pid}")
                                    // zmienna ze ścieżki
    public PersonModel updatePerson(@PathVariable("pid") UUID pid,
                             @RequestBody Person person,
                             @AuthenticationPrincipal AppUser user) {
        person.setPid(pid);
        Person personAdded = personService.updatePerson(person, user);
        return new PersonModelAssembler().toModel(personAdded);
    }

    @DeleteMapping("/{pid}")
    public void deletePerson(@PathVariable("pid") UUID pid,
                             @AuthenticationPrincipal AppUser user) {
        personService.removePerson(pid, user);
    }
}
