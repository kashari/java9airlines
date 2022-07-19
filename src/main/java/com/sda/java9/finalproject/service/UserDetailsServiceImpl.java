package com.sda.java9.finalproject.service;

import com.sda.java9.finalproject.dao.AppUserDAO;
import com.sda.java9.finalproject.dto.AppUserDTO;
import com.sda.java9.finalproject.security.model.UserDetailsImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service @RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final AppUserDAO appUserDAO;
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUserDTO user = appUserDAO.findByUsername(username);
        return UserDetailsImpl.build(user);
    }
}
