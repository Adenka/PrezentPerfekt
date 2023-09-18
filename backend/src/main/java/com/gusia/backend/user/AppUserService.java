package com.gusia.backend.user;

import com.gusia.backend.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// Jakiś serwis gotowy stworzony specjalnie z myślą o mnie i moich problemach.
@Service
public class AppUserService implements UserDetailsService {
    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public AppUserService(AppUserRepository appUserRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.appUserRepository = appUserRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    @Transactional // Wydaje mi się, że tu powinno być to Transactional, nie w repository.
    // UserDetails to interfejs, my zwracamy jego implementację.
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return appUserRepository.findByUsername(username).orElseThrow(ObjectNotFoundException::new);
    }

    @Transactional
    public void singUpUser(AppUser user) {
        if (appUserRepository.findByUsername(user.getUsername()).isPresent()) {
            // TODO - custom exception
            throw new IllegalStateException("Username taken!");
        }

        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());

        user.setPassword(encodedPassword);

        appUserRepository.save(user);
    }
}
