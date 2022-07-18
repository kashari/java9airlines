package com.sda.java9.finalproject.dao;

import com.sda.java9.finalproject.dto.AppUserDTO;
import com.sda.java9.finalproject.generics.AirlinesMapper;
import com.sda.java9.finalproject.generics.GenericDAO;
import com.sda.java9.finalproject.security.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component @RequiredArgsConstructor
public class AppUserDAO implements GenericDAO<AppUserDTO> {

    private final AppUserRepository appUserRepository;

    @Override
    public List<AppUserDTO> findAll() {
        return appUserRepository.findAll().stream().map(AirlinesMapper::mapAppUserToDTO).collect(Collectors.toList());
    }

    @Override
    public AppUserDTO findById(Long id) {
        return appUserRepository.findById(id).map(AirlinesMapper::mapAppUserToDTO).orElse(null);
    }

    @Override
    public void save(AppUserDTO appUser) {
        appUserRepository.save(AirlinesMapper.mapAppUserDTOToEntity(appUser));
    }

    @Override
    public void deleteById(Long id) {
        appUserRepository.deleteById(id);
    }

    public void saveAll(Set<AppUserDTO> appUserDTOS){
        appUserRepository.saveAll(appUserDTOS.stream().map(AirlinesMapper::mapAppUserDTOToEntity).collect(Collectors.toSet()));
    }
}
