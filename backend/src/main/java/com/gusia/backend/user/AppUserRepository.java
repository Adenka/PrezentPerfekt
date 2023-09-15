package com.gusia.backend.user;

import org.springframework.stereotype.Repository;

import java.util.Optional;

// Mam wrażenie, że dawanie tutaj @Transactional jest błędem zgodnie z tym,
// co mam napisane na tablicy.

@Repository // TODO - Czy to dawać? W innych przypadkach działa bez.
public interface AppUserRepository {
    Optional<AppUser> findByUsername(String username);
}
