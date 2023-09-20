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
public class PersonController {
    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    // ta anotacja jest z założenia do GET
    // inne trzeba wyspecyfikować
    @RequestMapping("/api/people")
    public List<Person> getPeople() {
        return personService.getPeople();
    }

    @RequestMapping("/api/people/{pid}")
    public Person getPerson(@PathVariable("pid") UUID pid) {
        return personService.getPerson(pid);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST, value="/api/people")
    // @RequestBody - w request payload będzie reprezentacja JSON tego obiektu
    public Person addPerson(@RequestBody Person person,
                          @AuthenticationPrincipal AppUser user) {
        System.out.println(person);
        Person savedPerson = personService.addPerson(person, user);
        System.out.println(savedPerson);
        return savedPerson;
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.PUT, value = "/api/people/{pid}")
    // @PathVariable - zmienna ze ścieżki
    public void updatePerson(@PathVariable("pid") UUID pid,
                             //TODO - czy da się zrobić żeby tylko String czytać z RequestBody?
                             @RequestBody Person person,
                             @AuthenticationPrincipal AppUser user) {
        //TODO - sprawdzenie uprawnień
        person.setPid(pid);
        personService.updatePerson(person, user);
    }

    //TODO - Cross?
    @RequestMapping(method = RequestMethod.DELETE, value = "/api/people/{pid}")
    public void deletePerson(@PathVariable("pid") UUID pid) {
        //TODO - sprawdzenie uprawnień
        personService.removePerson(pid);

    }
}
