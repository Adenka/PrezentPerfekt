package com.gusia.backend.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// na początku Spring przejrzy mój epicki kod i znajdzie te anotacje,
// więc nie trzeba się pocić, żeby coś gdzieś pododawać

// kontroler do jakiejś ścieżki
@RestController
public class HomeController {
    private final HomeService homeService;

    @Autowired
    public HomeController(HomeService homeService) {
        this.homeService = homeService;
    }

    // ta anotacja jest z założenia do GET
    // inne trzeba wyspecyfikować
    @RequestMapping("/")
    public List<Person> getPeople() {
        return homeService.getPeople();
    }

    @RequestMapping(method = RequestMethod.POST, value="/")
    // @RequestBody - w request payload będzie reprezentacja JSON tego obiektu
    // TODO - generowanie id samemu
    public void addPerson(@RequestBody Person person) {
        //TODO - sprawdzenie uprawnień
        homeService.addPerson(person);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{pid}")
    // @PathVariable - zmienna ze ścieżki
    public void updatePerson(@PathVariable("pid") int pid,
                             //TODO - czy da się zrobić żeby tylko String czytać z RequestBody?
                             @RequestBody Person person) {
        //TODO - sprawdzenie uprawnień
        person.setPid(pid);
        homeService.updatePerson(person);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{pid}")
    public void deletePerson(@PathVariable("pid") int pid) {
        //TODO - sprawdzenie uprawnień
        homeService.removePerson(pid);

    }
}
