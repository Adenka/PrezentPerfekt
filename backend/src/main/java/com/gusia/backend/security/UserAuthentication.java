package com.gusia.backend.security;

import com.gusia.backend.exceptions.NoAccessException;
import com.gusia.backend.exceptions.ObjectNotFoundException;
import com.gusia.backend.person.Person;
import com.gusia.backend.person.PersonRepository;
import com.gusia.backend.user.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserAuthentication {
    private final PersonRepository personRepository;

    @Autowired
    public UserAuthentication(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public void assertPidFromUser(UUID pid, AppUser user) {
        Person person = personRepository.findById(pid).orElseThrow(
                ObjectNotFoundException::new
        );
        assertPersonFromUser(person, user);
    }

    public void assertPersonFromUser(Person person, AppUser user) {
        Integer foundPersonUserId = person.getUser().getId();
        if (!foundPersonUserId.equals(user.getId())) {
            throw new NoAccessException("This person is not assigned to this user!");
        }
    }
}
