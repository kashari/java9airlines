package com.sda.java9.finalproject.security.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter @Setter @Component
public class LoginModel {
    private String username;
    private String password;
}
