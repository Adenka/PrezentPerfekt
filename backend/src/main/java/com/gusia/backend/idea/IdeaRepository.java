package com.gusia.backend.idea;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface IdeaRepository extends CrudRepository<Idea, UUID> {
    List<Idea> findByPersonPid(UUID pid);
}
