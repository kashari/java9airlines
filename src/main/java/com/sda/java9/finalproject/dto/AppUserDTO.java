package com.sda.java9.finalproject.dto;

import com.sda.java9.finalproject.security.entity.AppRole;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter @Setter
public class AppUserDTO {
    private Long id;
    private String username;
    private String email;
    private String password;
    private Set<AppRole> roles = new HashSet<>();

}
