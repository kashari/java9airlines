package com.sda.java9.finalproject.controller;

import com.sda.java9.finalproject.dto.PostDTO;
import com.sda.java9.finalproject.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping //@PreAuthorize("hasAuthority('ADMIN')")
    public PostDTO save(@RequestBody PostDTO post, Authentication authentication){
        postService.save(post, authentication);
        return post;
    }

    @PutMapping //@PreAuthorize("hasAuthority('ADMIN')")
    public PostDTO update(@RequestBody PostDTO post, Authentication authentication){
        postService.save(post, authentication);
        return post;
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable("id") Long id){
        postService.deleteById(id);
        return String.format("Post with id %d was deleted successfully.", id);
    }

    @PutMapping("/{id}/comment")
    public void addReviewToPost(@PathVariable("id") Long id, @RequestBody String review, Authentication authentication){
        postService.addReviewToPost(id, review ,authentication);
    }
}
