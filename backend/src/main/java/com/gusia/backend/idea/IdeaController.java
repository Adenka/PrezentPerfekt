package com.gusia.backend.idea;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//TODO - basically to samo co w HomeController
//TODO - check, czy pid i iid match

@RestController
public class IdeaController {
    private final IdeaService ideaService;

    @Autowired
    public IdeaController(IdeaService ideaService) {
        this.ideaService = ideaService;
    }

    @RequestMapping("/people/{pid}/ideas")
    public List<Idea> getIdeas(@PathVariable("pid") int pid) {
        return ideaService.getIdeas(pid);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/people/{pid}/ideas")
    public void addIdea(@RequestBody Idea idea, @PathVariable("pid") int pid) {
        idea.setPerson(pid);
        ideaService.addIdea(idea);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/people/{pid}/ideas/{iid}")
    public void updateIdea(@PathVariable("iid") int iid,
                           @RequestBody Idea idea, @PathVariable int pid) {
        idea.setIid(iid);
        idea.setPerson(pid);
        ideaService.updateIdea(idea);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/people/{pid}/ideas/{iid}")
    public void deleteIdea(@PathVariable("iid") int iid) {
        ideaService.deleteIdea(iid);
    }
}
