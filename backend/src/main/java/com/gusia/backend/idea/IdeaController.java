package com.gusia.backend.idea;

import com.gusia.backend.user.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

//TODO - check, czy dodajemy od zalogowanego użytkownika

@RestController
@RequestMapping(path="/api/people/{pid}/ideas")
public class IdeaController {
    private final IdeaService ideaService;

    @Autowired
    public IdeaController(IdeaService ideaService) {
        this.ideaService = ideaService;
    }

    @GetMapping
    public List<Idea> getIdeas(@PathVariable("pid") UUID pid,
                               @AuthenticationPrincipal AppUser user) {
        return ideaService.getIdeas(pid, user);
    }

    @CrossOrigin
    @PostMapping
    public void addIdea(@RequestBody Idea idea,
                        @PathVariable("pid") UUID pid,
                        @AuthenticationPrincipal AppUser user) {
        ideaService.addIdea(idea, pid, user);
    }

    @CrossOrigin
    @PutMapping("/{iid}")
    public void updateIdea(@RequestBody Idea idea,
                           @PathVariable("iid") UUID iid,
                           @PathVariable UUID pid,
                           @AuthenticationPrincipal AppUser user) {
        idea.setIid(iid);
        ideaService.updateIdea(idea, pid, user);
    }

    //TODO - Cross?
    @DeleteMapping("/{iid}")
    public void deleteIdea(@PathVariable("iid") UUID iid,
                           @AuthenticationPrincipal AppUser user) {
        ideaService.deleteIdea(iid);
    }
}
