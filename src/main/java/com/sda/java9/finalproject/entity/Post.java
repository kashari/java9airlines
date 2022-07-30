package com.sda.java9.finalproject.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity @Getter @Setter
public class Post {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String imageURL;
    @ManyToOne
    private AppUser author;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Review> reviews;
    @CreationTimestamp
    private LocalDateTime createdAt;

}
