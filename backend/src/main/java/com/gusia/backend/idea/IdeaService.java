package com.gusia.backend.idea;

import com.gusia.backend.exceptions.NoAccessException;
import com.gusia.backend.exceptions.ObjectNotFoundException;
import com.gusia.backend.person.Person;
import com.gusia.backend.person.PersonRepository;
import com.gusia.backend.security.UserAuthentication;
import com.gusia.backend.user.AppUser;
import com.gusia.backend.validators.ObjectValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.*;

@Service
@Transactional
public class IdeaService {
    private final IdeaRepository ideaRepository;
    private final PersonRepository personRepository;
    private final UserAuthentication userAuthentication;

    @Autowired
    public IdeaService(IdeaRepository ideaRepository,
                       PersonRepository personRepository,
                       UserAuthentication userAuthentication) {
        this.ideaRepository = ideaRepository;
        this.personRepository = personRepository;
        this.userAuthentication = userAuthentication;
    }

    private void assertIidFromPid(UUID iid, UUID pid) {
        Idea idea = ideaRepository.findById(iid).orElseThrow(
                ObjectNotFoundException::new
        );
        assertIdeaFromPid(idea, pid);
    }

    private void assertIdeaFromPid(Idea idea, UUID pid) {
        UUID IdeaPersonId = idea.getPerson().getPid();
        if (!IdeaPersonId.equals(pid)) {
            throw new NoAccessException("This idea is not assigned to this person!");
        }
    }

    @Transactional(readOnly = true)
    public List<Idea> getIdeas(UUID pid, AppUser user) {
        userAuthentication.assertPidFromUser(pid, user);
        return ideaRepository.findByPersonPid(pid);
    }

    @Transactional
    public Idea addIdea(Idea idea, UUID pid, AppUser user) {
        userAuthentication.assertPidFromUser(pid, user);
        idea.setPerson(personRepository.findPerson(pid));

        ObjectValidator<Idea> validator = new ObjectValidator<>();
        validator.validate(idea);
        return ideaRepository.save(idea);
    }

    @Transactional
    public void updateIdea(Idea idea, UUID pid, AppUser user) {
        userAuthentication.assertPidFromUser(pid, user);
        idea.setPerson(personRepository.findPerson(pid));
        assertIdeaFromPid(idea, pid);

        ObjectValidator<Idea> validator = new ObjectValidator<>();
        validator.validate(idea);
        ideaRepository.save(idea);
    }

    //TODO - jak zrobić żeby sygnalizować czy delete się udał czy nie
    @CrossOrigin
    @Transactional
    public void deleteIdea(UUID iid, UUID pid, AppUser user) {
        userAuthentication.assertPidFromUser(pid, user);
        assertIidFromPid(iid, pid);
        ideaRepository.deleteById(iid);
    }
}
