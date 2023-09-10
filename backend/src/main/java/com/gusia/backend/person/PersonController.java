package com.gusia.backend.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @RequestMapping("/people")
    public List<Person> getPeople() {
        return personService.getPeople();
    }

    @RequestMapping("/people/{pid}")
    public Person getPerson(@PathVariable("pid") int pid) {
        return personService.getPerson(pid);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST, value="/people")
    // @RequestBody - w request payload będzie reprezentacja JSON tego obiektu
    // TODO - generowanie id samemu
    public void addPerson(@RequestBody Person person) {
        //TODO - sprawdzenie uprawnień
        System.out.println(person);
        personService.addPerson(person);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/people/{pid}")
    // @PathVariable - zmienna ze ścieżki
    public void updatePerson(@PathVariable("pid") int pid,
                             //TODO - czy da się zrobić żeby tylko String czytać z RequestBody?
                             @RequestBody Person person) {
        //TODO - sprawdzenie uprawnień
        person.setPid(pid);
        personService.updatePerson(person);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/people/{pid}")
    public void deletePerson(@PathVariable("pid") int pid) {
        //TODO - sprawdzenie uprawnień
        personService.removePerson(pid);

    }
}
