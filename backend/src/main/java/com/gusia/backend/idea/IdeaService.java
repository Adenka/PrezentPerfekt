package com.gusia.backend.idea;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IdeaService {
    private final IdeaRepository ideaRepository;

    @Autowired
    public IdeaService(IdeaRepository ideaRepository) {
        this.ideaRepository = ideaRepository;
    }

    public List<Idea> getIdeas(int pid) {
        return ideaRepository.findByPersonPid(pid);
    }

    public void addIdea(Idea idea) {
        ideaRepository.save(idea);
    }

    public void updateIdea(Idea idea) {
        ideaRepository.save(idea);
    }

    public void deleteIdea(int iid) {
        ideaRepository.deleteById(iid);
    }
}
