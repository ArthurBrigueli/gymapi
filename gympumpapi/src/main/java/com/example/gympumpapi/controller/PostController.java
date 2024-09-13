package com.example.gympumpapi.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.gympumpapi.entity.Friend;
import com.example.gympumpapi.entity.Post;
import com.example.gympumpapi.service.PostService;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    
    PostService postService;

    public PostController(PostService postService){
        this.postService = postService;
    }




    @PostMapping("/post/create")
    public String createPost(@RequestBody Post post){
        return postService.createPost(post);
    }


    @GetMapping("/{idUser}")
    public List<Post> getAllPost(@PathVariable Long idUser){
        return postService.getAllPost(idUser);
    }


}
