package com.gusia.backend.idea;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IdeaRepository extends CrudRepository<Idea, Integer> {
    List<Idea> findByPersonPid(Integer pid);
}