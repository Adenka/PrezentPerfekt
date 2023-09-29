package com.gusia.backend.idea;

import com.gusia.backend.user.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/people/{pid}/ideas")
public class IdeaController {
    private final IdeaService ideaService;

    @Autowired
    public IdeaController(IdeaService ideaService) {
        this.ideaService = ideaService;
    }

    @GetMapping
    public CollectionModel<IdeaModel> getIdeas(@PathVariable(value = "pid") UUID pid,
                                          @AuthenticationPrincipal AppUser user) {
        List<Idea> ideasList = ideaService.getIdeas(pid, user);
        CollectionModel<IdeaModel> ideaModels = new IdeaModelAssembler().toCollectionModel(ideasList);

        ideaModels.add(
                linkTo(methodOn(IdeaController.class).getIdeas(pid, user)).withSelfRel()
        );

        return ideaModels;
    }

    @PostMapping
    public IdeaModel addIdea(@RequestBody Idea idea,
                        @PathVariable("pid") UUID pid,
                        @AuthenticationPrincipal AppUser user) {
        Idea ideaSaved = ideaService.addIdea(idea, pid, user);
        return new IdeaModelAssembler().toModel(ideaSaved);
    }

    @PutMapping("/{iid}")
    public void updateIdea(@RequestBody Idea idea,
                           @PathVariable("iid") UUID iid,
                           @PathVariable("pid") UUID pid,
                           @AuthenticationPrincipal AppUser user) {
        System.out.println(idea);
        idea.setIid(iid);
        ideaService.updateIdea(idea, pid, user);
    }

    @DeleteMapping("/{iid}")
    public void deleteIdea(@PathVariable("iid") UUID iid,
                           @PathVariable("pid") UUID pid,
                           @AuthenticationPrincipal AppUser user) {
        ideaService.deleteIdea(iid, pid, user);
    }
}
