package com.sda.java9.finalproject.service;

import com.sda.java9.finalproject.dao.AppUserDAO;
import com.sda.java9.finalproject.dao.PostDAO;
import com.sda.java9.finalproject.dto.AppUserDTO;
import com.sda.java9.finalproject.dto.PostDTO;
import com.sda.java9.finalproject.dto.ReviewDTO;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service @RequiredArgsConstructor
public class PostService {

    private final PostDAO postDAO;
    private final AppUserDAO appUserDAO;

    @Value("${app.uploads.root}")
    private String root;

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
        postDAO.save(post);
    }

    @Transactional
    public void addImageToPost(Long id, MultipartFile file){
        PostDTO post = postDAO.findById(id);
        uploadFile(file);
        System.out.println(Arrays.toString((root + file.getOriginalFilename()).split("\\/([^\\/]+)\\/([^\\/]+)\\/([^\\/]+)$")));
        String path = root + file.getOriginalFilename();
        Pattern pattern = Pattern.compile("\\/([^\\/]+)\\/([^\\/]+)\\/([^\\/]+)$");
        Matcher matcher = pattern.matcher(path);
        if (matcher.find())
        {
            post.setImageURL(matcher.group());
        }
        postDAO.save(post);
    }


    private void uploadFile(MultipartFile file){
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        try {
            Path path = Paths.get(root + fileName);
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
