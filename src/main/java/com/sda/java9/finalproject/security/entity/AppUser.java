package com.sda.java9.finalproject.security.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter @Setter // later to make the entities and mappings
public class AppUser {
    private Long id;
    private String name;
    private String email;
    private String password;
    private String profession;
    private Set<AppRole> roles;
}
