package com.sda.java9.finalproject.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter
public class PostDTO {
    private Long id;
    private String title;
    private String description;
    private AppUserDTO author;
    private List<ReviewDTO> reviews;
    private LocalDateTime createdAt;
}
