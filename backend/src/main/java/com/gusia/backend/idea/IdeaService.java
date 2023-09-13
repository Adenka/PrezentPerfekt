package com.gusia.backend.idea;

import com.gusia.backend.exceptions.ObjectNotFoundException;
import com.gusia.backend.person.PersonRepository;
import com.gusia.backend.validators.ObjectValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class IdeaService {
    private final IdeaRepository ideaRepository;
    private final PersonRepository personRepository;

    @Autowired
    public IdeaService(IdeaRepository ideaRepository, PersonRepository personRepository) {
        this.ideaRepository = ideaRepository;
        this.personRepository = personRepository;
    }

    @Transactional(readOnly = true)
    public List<Idea> getIdeas(UUID pid) {
        return ideaRepository.findByPersonPid(pid);
    }

    @Transactional
    public void addIdea(Idea idea, UUID pid) {
        idea.setPerson(personRepository.findById(pid).orElseThrow(
                ObjectNotFoundException::new
        ));

        ObjectValidator<Idea> validator = new ObjectValidator<>();
        validator.validate(idea);
        ideaRepository.save(idea);
    }

    @Transactional
    public void updateIdea(Idea idea, UUID pid) {
        idea.setPerson(personRepository.findById(pid).orElseThrow(
                ObjectNotFoundException::new
        ));

        ObjectValidator<Idea> validator = new ObjectValidator<>();
        validator.validate(idea);
        ideaRepository.save(idea);
    }

    @Transactional
    public void deleteIdea(UUID iid) {
        ideaRepository.deleteById(iid);
    }
}
