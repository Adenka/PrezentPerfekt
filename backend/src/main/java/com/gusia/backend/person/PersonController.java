package com.gusia.backend.person;

import com.gusia.backend.user.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

// na początku Spring przejrzy mój epicki kod i znajdzie te anotacje,
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
    public List<Person> getPeople(@AuthenticationPrincipal AppUser user) {
        return personService.getPeople(user);
    }

    @GetMapping("/{pid}")
    public Person getPerson(@PathVariable("pid") UUID pid,
                            @AuthenticationPrincipal AppUser user) {
        return personService.getPerson(pid, user);
    }

    @CrossOrigin
    @PostMapping
    // @RequestBody - w request payload będzie reprezentacja JSON tego obiektu
    public Person addPerson(@RequestBody Person person,
                            @AuthenticationPrincipal AppUser user) {
        return personService.addPerson(person, user);
    }

    @CrossOrigin
    @PutMapping("/{pid}")
    // @PathVariable - zmienna ze ścieżki
    public void updatePerson(@PathVariable("pid") UUID pid,
                             @RequestBody Person person,
                             @AuthenticationPrincipal AppUser user) {
        person.setPid(pid);
        personService.updatePerson(person, user);
    }

    //TODO - Cross?
    @DeleteMapping("/{pid}")
    public void deletePerson(@PathVariable("pid") UUID pid,
                             @AuthenticationPrincipal AppUser user) {
        personService.removePerson(pid, user);
    }
}
