package com.gusia.backend.registration;

import com.gusia.backend.user.AppUser;
import com.gusia.backend.user.AppUserRole;
import com.gusia.backend.user.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {
    private final AppUserService appUserService;

    @Autowired
    public RegistrationService(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    public String register(RegistrationRequest request) {
        appUserService.singUpUser(
                new AppUser(request.getUsername(), request.getPassword(), AppUserRole.USER)
        );

        return "IT'S NOW OOON";
    }
}
