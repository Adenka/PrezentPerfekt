package com.gusia.backend.person;

import com.gusia.backend.exceptions.ObjectNotFoundException;
import com.gusia.backend.security.UserAuthentication;
import com.gusia.backend.user.AppUser;
import com.gusia.backend.validators.ObjectValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class PersonService {
    private final PersonRepository personRepository;
    private final UserAuthentication userAuthentication;
    private final PersonUtils personUtils;

    @Autowired
    public PersonService(PersonRepository personRepository,
                         UserAuthentication userAuthentication,
                         PersonUtils personUtils) {
        this.personRepository = personRepository;
        this.userAuthentication = userAuthentication;
        this.personUtils = personUtils;
    }

    @Transactional(readOnly = true)
    public List<Person> getPeople(AppUser user) {
        return new ArrayList<>(personRepository.findByUserId(user.getId()));
    }

    @Transactional(readOnly = true)
    public Person getPerson(UUID pid, AppUser user) {
        Person person = personUtils.findPerson(pid);
        userAuthentication.assertPersonFromUser(person, user);

        return person;
    }

    @Transactional
    public Person addPerson(Person person, AppUser user) {
        ObjectValidator<Person> validator = new ObjectValidator<>();
        validator.validate(person);

        person.setUser(user);
        // tu chyba nie trzeba checkować usera
        return personRepository.save(person);
    }

    @Transactional
    public void updatePerson(Person person, AppUser user) {
        ObjectValidator<Person> validator = new ObjectValidator<>();
        validator.validate(person);

        if (personRepository.findById(person.getPid()).isPresent()) {
            throw new ObjectNotFoundException();
        }

        person.setUser(user);

        userAuthentication.assertPersonFromUser(person, user);
        personRepository.save(person);
    }

    @Transactional
    public void removePerson(UUID pid, AppUser user) {
        Person person = personUtils.findPerson(pid);
        userAuthentication.assertPersonFromUser(person, user);
        personRepository.deleteById(pid);
    }
}
