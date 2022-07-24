package com.sda.java9.finalproject.repository;

import com.sda.java9.finalproject.entity.AppRole;
import com.sda.java9.finalproject.entity.AppUser;
import com.sda.java9.finalproject.entity.RoleName;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Component @RequiredArgsConstructor @Transactional
public class DatabaseSeeder implements CommandLineRunner {

    private final AppUserRepository appUserRepository;
    private final AppRoleRepository appRoleRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        initializeRoles();
        initializeUser();
    }

    public void initializeRoles(){
        if (appRoleRepository.count() == 0) {
            Set<AppRole> roles = new HashSet<>();
            AppRole USER = new AppRole();
            USER.setName(RoleName.USER);
            AppRole ADMIN = new AppRole();
            ADMIN.setName(RoleName.ADMIN);
            AppRole ROOT = new AppRole();
            ROOT.setName(RoleName.ROOT);
            roles.add(USER);
            roles.add(ADMIN);
            roles.add(ROOT);
            appRoleRepository.saveAll(roles);
        }
    }

    private void initializeUser() {
        if (appUserRepository.count() == 0 ){
            AppUser root = new AppUser();
            root.setUsername("superuser");
            root.setEmail("superuser@java9airlines.com");
            root.setPassword(passwordEncoder.encode("morethanroot"));
            root.setIsEnabled(true);
            root.getRoles().add(appRoleRepository.findByName(RoleName.ROOT).orElse(null));
            root.getRoles().add(appRoleRepository.findByName(RoleName.ADMIN).orElse(null));
            appUserRepository.save(root);
        }
    }
}
