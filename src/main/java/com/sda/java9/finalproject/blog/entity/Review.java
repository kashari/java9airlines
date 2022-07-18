package com.sda.java9.finalproject.blog.entity;

import com.sda.java9.finalproject.security.entity.AppUser;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity @Getter @Setter
public class Review {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String body;
    @ManyToOne
    private AppUser user;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
