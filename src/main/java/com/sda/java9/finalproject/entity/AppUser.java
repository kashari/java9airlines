package com.sda.java9.finalproject.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter @Setter @Entity @NoArgsConstructor
public class AppUser {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    private String password;

    private String profession;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<AppRole> roles = new HashSet<>();

    private Boolean isEnabled;

    public AppUser(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
