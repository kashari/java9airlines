package com.sda.java9.finalproject.dto;

import com.sda.java9.finalproject.entity.AppUser;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class ReviewDTO {

    private Long id;
    private String body;
    private AppUserDTO user;
    private LocalDateTime createdAt;
}
