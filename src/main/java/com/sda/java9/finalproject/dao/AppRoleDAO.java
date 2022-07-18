package com.sda.java9.finalproject.dao;

import com.sda.java9.finalproject.dto.AirportDTO;
import com.sda.java9.finalproject.dto.AppRoleDTO;
import com.sda.java9.finalproject.generics.AirlinesMapper;
import com.sda.java9.finalproject.generics.GenericDAO;
import com.sda.java9.finalproject.repository.AppRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component @RequiredArgsConstructor
public class AppRoleDAO implements GenericDAO<AppRoleDTO> {

    private final AppRoleRepository appRoleRepository;


    @Override
    public List<AppRoleDTO> findAll() {
        return appRoleRepository.findAll().stream().map(AirlinesMapper::mapAppRoleToDTO).collect(Collectors.toList());
    }

    @Override
    public AppRoleDTO findById(Long id) {
        return appRoleRepository.findById(id).map(AirlinesMapper::mapAppRoleToDTO).orElse(null);
    }

    @Override
    public void save(AppRoleDTO appRole) {
        appRoleRepository.save(AirlinesMapper.mapAppRoleDTOToEntity(appRole));
    }

    @Override
    public void deleteById(Long id) {
        appRoleRepository.deleteById(id);
    }

    public void saveAll(Set<AppRoleDTO> appRoleDTOS){
        appRoleRepository.saveAll(appRoleDTOS.stream().map(AirlinesMapper::mapAppRoleDTOToEntity).collect(Collectors.toSet()));
    }
}
