package com.sda.java9.finalproject.service;

import com.sda.java9.finalproject.dto.AppUserDTO;
import com.sda.java9.finalproject.entity.AppUser;
import com.sda.java9.finalproject.generics.AirlinesMapper;
import com.sda.java9.finalproject.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service @RequiredArgsConstructor
public class AdminAreaService {

    private final AppUserRepository appUserRepository;


    public List<AppUserDTO> findAll(){
        return appUserRepository.findAll().stream().map(AirlinesMapper::mapAppUserToDTO).collect(Collectors.toList());
    }

    // basically this is used to update fields, give permissions, and to delete a certain user ... may add is active is enabled later
    public void update(AppUserDTO appUser){
        appUserRepository.save(AirlinesMapper.mapAppUserDTOToEntity(appUser));
    }


    public void deleteById(Long id){
        appUserRepository.deleteById(id);
    }
}
