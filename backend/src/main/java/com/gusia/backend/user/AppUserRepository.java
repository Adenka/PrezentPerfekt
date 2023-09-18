package com.gusia.backend.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// Mam wrażenie, że dawanie tutaj @Transactional jest błędem zgodnie z tym,
// co mam napisane na tablicy.

public interface AppUserRepository extends JpaRepository<AppUser, Integer> {
    Optional<AppUser> findByUsername(String username);
}
