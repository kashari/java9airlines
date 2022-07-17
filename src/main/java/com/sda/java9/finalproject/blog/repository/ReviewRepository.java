package com.sda.java9.finalproject.blog.repository;

import com.sda.java9.finalproject.blog.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
