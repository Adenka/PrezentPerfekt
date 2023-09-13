package com.gusia.backend.idea;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

//TODO - check, czy pid i iid match

@RestController
public class IdeaController {
    private final IdeaService ideaService;

    @Autowired
    public IdeaController(IdeaService ideaService) {
        this.ideaService = ideaService;
    }

    @RequestMapping("/people/{pid}/ideas")
    public List<Idea> getIdeas(@PathVariable("pid") UUID pid) {
        return ideaService.getIdeas(pid);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST, value = "/people/{pid}/ideas")
    public void addIdea(@RequestBody Idea idea, @PathVariable("pid") UUID pid) {
        ideaService.addIdea(idea, pid);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.PUT, value = "/people/{pid}/ideas/{iid}")
    public void updateIdea(@PathVariable("iid") UUID iid,
                           @RequestBody Idea idea, @PathVariable UUID pid) {
        idea.setIid(iid);
        ideaService.updateIdea(idea, pid);
    }

    //TODO - Cross?
    @RequestMapping(method = RequestMethod.DELETE, value = "/people/{pid}/ideas/{iid}")
    public void deleteIdea(@PathVariable("iid") UUID iid) {
        ideaService.deleteIdea(iid);
    }
}
