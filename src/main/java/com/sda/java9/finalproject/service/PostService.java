package com.sda.java9.finalproject.service;

import com.sda.java9.finalproject.dao.AppUserDAO;
import com.sda.java9.finalproject.dao.PostDAO;
import com.sda.java9.finalproject.dto.AppUserDTO;
import com.sda.java9.finalproject.dto.PostDTO;
import com.sda.java9.finalproject.dto.ReviewDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service @RequiredArgsConstructor
public class PostService {

    private final PostDAO postDAO;
    private final AppUserDAO appUserDAO;

    public List<PostDTO> findAll(){
        return postDAO.findAll();
    }

    public PostDTO findById(Long id){
        return postDAO.findById(id);
    }

    public void save(PostDTO post, Authentication authentication){
        AppUserDTO userDTO = appUserDAO.findByUsername(authentication.getName());
        post.setAuthor(userDTO);
        postDAO.save(post);
    }

    public void deleteById(Long id){
        postDAO.deleteById(id);
    }

    @Transactional
    public void addReviewToPost(Long id, String body, Authentication authentication){
        PostDTO post = postDAO.findById(id);
        ReviewDTO review = new ReviewDTO();
        review.setBody(body);
        AppUserDTO appUser = appUserDAO.findByUsername(authentication.getName());
        review.setUser(appUser);
        review.setCreatedAt(LocalDateTime.now());
        post.getReviews().add(review);
    }
}
