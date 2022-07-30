package com.sda.java9.finalproject.controller;

import com.sda.java9.finalproject.dto.PostDTO;
import com.sda.java9.finalproject.service.PostService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController @RequiredArgsConstructor @RequestMapping("/news") @CrossOrigin("http://localhost:4200")
public class PostController {

    private final PostService postService;

    @GetMapping
    public List<PostDTO> findAll(){
        return postService.findAll();
    }

    @GetMapping("/{id}")
    public PostDTO findById(@PathVariable("id") Long id){
        return postService.findById(id);
    }

    @PostMapping//@PreAuthorize("hasAuthority('ADMIN')")
    public PostDTO save(@RequestBody PostDTO post, Authentication authentication){
        postService.save(post, authentication);
        return post;
    }

    @PutMapping //@PreAuthorize("hasAuthority('ADMIN')")
    public PostDTO update(@RequestBody PostDTO post, Authentication authentication){
        postService.save(post, authentication);
        return post;
    }

    @PostMapping("{id}/upload-image")
    public ResponseEntity<?> uploadImage(@PathVariable("id") Long id, @Valid @RequestPart(name = "file") MultipartFile file){
        postService.addImageToPost(id, file);
        return ResponseEntity.ok("Image updated successfully.");
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable("id") Long id){
        postService.deleteById(id);
        return String.format("Post with id %d was deleted successfully.", id);
    }

    @PostMapping("/{id}/comment")
    public String addReviewToPost(@PathVariable("id") Long id, @RequestBody String review, Authentication authentication){
        postService.addReviewToPost(id, review ,authentication);
        return review;
    }
}
