package com.sda.java9.finalproject.security.service;

import com.sda.java9.finalproject.security.entity.AppUser;
import com.sda.java9.finalproject.security.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @RequiredArgsConstructor
public class AdminAreaService {

    private final AppUserRepository appUserRepository;


    public List<AppUser> findAll(){
        return appUserRepository.findAll();
    }

    // basically this is used to update fields, give permissions, and to delete a certain user ... may add is active is enabled later
    public void update(AppUser appUser){
        appUserRepository.save(appUser);
    }


    public void deleteById(Long id){
        appUserRepository.deleteById(id);
    }
}
