//TODO - czy to jest dobry pomysł? Czy powinnam rzucać jakoś wyjątki w repo?
package com.gusia.backend.person;

import com.gusia.backend.exceptions.ObjectNotFoundException;
import org.springframework.stereotype.Component;
import java.util.UUID;

@Component
public class PersonUtils {
    private final PersonRepository personRepository;

    public
    PersonUtils(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person findPerson(UUID pid) {
        return personRepository.findById(pid).orElseThrow(
                ObjectNotFoundException::new
        );
    }
}
