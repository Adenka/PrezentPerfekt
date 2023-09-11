package com.gusia.backend.idea;

import com.gusia.backend.person.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

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
        //TODO - null handling
        idea.setPerson(personRepository.findById(pid).orElse(null));
        ideaRepository.save(idea);
    }

    @Transactional
    public void updateIdea(Idea idea, UUID pid) {
        //TODO - null handling
        idea.setPerson(personRepository.findById(pid).orElse(null));
        ideaRepository.save(idea);
    }

    @Transactional
    public void deleteIdea(UUID iid) {
        ideaRepository.deleteById(iid);
    }
}
