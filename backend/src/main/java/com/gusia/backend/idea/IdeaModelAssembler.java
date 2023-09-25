package com.gusia.backend.idea;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

public class IdeaModelAssembler extends RepresentationModelAssemblerSupport<Idea, IdeaModel> {

    public IdeaModelAssembler() {
        super(IdeaController.class, IdeaModel.class);
    }

    @Override
    public IdeaModel toModel(Idea idea) {
        return createModelWithId(idea.getIid(), idea, idea.getPerson().getPid());
    }

    @Override
    protected IdeaModel instantiateModel(Idea idea) {
        return new IdeaModel(idea);
    }
}
