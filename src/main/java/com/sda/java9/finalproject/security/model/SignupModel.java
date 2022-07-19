package com.sda.java9.finalproject.security.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Component @Data
public class SignupModel {
        @NotBlank @Size(min = 3, max = 20)
        private String username;

        @NotBlank @Size(max = 50) @Email
        private String email;

        @NotBlank @Size(min = 6, max = 40)
        private String password;

}
