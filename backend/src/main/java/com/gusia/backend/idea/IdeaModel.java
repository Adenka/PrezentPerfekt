package com.gusia.backend.idea;

import org.springframework.hateoas.RepresentationModel;

import java.util.UUID;

public class IdeaModel extends RepresentationModel<IdeaModel> {
    private final String title;

    public IdeaModel(Idea idea) {
        this.title = idea.getTitle();
    }

    public String getTitle() {
        return title;
    }
}
