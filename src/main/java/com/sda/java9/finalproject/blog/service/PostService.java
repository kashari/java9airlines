package com.sda.java9.finalproject.blog.service;

import com.sda.java9.finalproject.blog.entity.Post;
import com.sda.java9.finalproject.blog.entity.Review;
import com.sda.java9.finalproject.blog.repository.PostRepository;
import com.sda.java9.finalproject.security.entity.AppUser;
import com.sda.java9.finalproject.security.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service @RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final AppUserRepository userRepository;

    public List<Post> findAll(){
        return postRepository.findAll();
    }

    public Post findById(Long id){
        return postRepository.findById(id).orElse(null);
    }

    public void save(Post post){
        postRepository.save(post);
    }

    public void deleteById(Long id){
        postRepository.deleteById(id);
    }

    @Transactional
    public void addReviewToPost(Long id, String body, Authentication authentication){
        Post post = postRepository.findById(id).orElseThrow(IllegalStateException::new);
        Review review = new Review();
        review.setBody(body);
        AppUser appUser = userRepository.findByUsername(authentication.getName()).orElseThrow(IllegalStateException::new);
        review.setUser(appUser);
        review.setCreatedAt(LocalDateTime.now());
        post.getReviews().add(review);
    }
}
