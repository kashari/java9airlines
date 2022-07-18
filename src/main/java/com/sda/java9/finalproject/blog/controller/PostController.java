package com.sda.java9.finalproject.blog.controller;

import com.sda.java9.finalproject.blog.entity.Post;
import com.sda.java9.finalproject.blog.model.ReviewModel;
import com.sda.java9.finalproject.blog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController @RequiredArgsConstructor @RequestMapping("/news") @CrossOrigin("http://localhost:4200")
public class PostController {

    private final PostService postService;

    @GetMapping
    public List<Post> findAll(){
        return postService.findAll();
    }

    @GetMapping("/{id}")
    public Post findById(@PathVariable("id") Long id){
        return postService.findById(id);
    }

    @PostMapping
    public Post save(@RequestBody Post post){
        postService.save(post);
        return post;
    }

    @PutMapping
    public Post update(@RequestBody Post post){
        postService.save(post);
        return post;
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable("id") Long id){
        postService.deleteById(id);
        return String.format("Post with id %d was deleted successfully.", id);
    }

    @PostMapping("/comment")
    public void addReviewToPost(@RequestBody ReviewModel review){
        postService.addReviewToPost(review.getId(), review.getBody());
    }
}
