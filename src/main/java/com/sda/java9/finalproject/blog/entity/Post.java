package com.sda.java9.finalproject.blog.entity;

import com.sda.java9.finalproject.security.entity.AppUser;
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
    private String description;  // basically the content separated in paragraphs
    @ManyToOne
    private AppUser author; // only users with role staff will have access to create this entity
    @OneToMany(cascade = CascadeType.ALL)
    private List<Review> reviews; // this is a typical post-comment relationship
    @CreationTimestamp
    private LocalDateTime createdAt; // decorator for when was created and may be used for sorting

}
