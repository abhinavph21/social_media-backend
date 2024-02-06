package com.example.SocialMediabackend.controller;

import com.example.SocialMediabackend.model.Post;
import com.example.SocialMediabackend.response.ApiResponse;
import com.example.SocialMediabackend.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class PostController {
    @Autowired
    PostService postService;
    @PostMapping("/posts/user/{userId}")
    public ResponseEntity<Post> createPost(@RequestBody Post post, @PathVariable Integer userId) throws Exception {
        try {
            Post newPost = postService.createPost(post, userId);
            return new ResponseEntity<>(newPost, HttpStatus.OK);
        } catch (Exception exception){
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
    @DeleteMapping("/posts/{postId}/users/{userId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId, @PathVariable Integer userId) throws Exception{
        try {
            String postMessage = postService.deletePost(postId, userId);
            ApiResponse res = new ApiResponse(postMessage, true);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception exception){
            return new ResponseEntity<>(new ApiResponse(exception.toString(), false), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
    @GetMapping("/posts/{postId}")
    public ResponseEntity<Post> findPostByIdHandler(@PathVariable Integer postId) throws Exception{
        Post post = postService.findPostById(postId);
        return new ResponseEntity<>(post, HttpStatus.ACCEPTED);
    }
    @GetMapping("/posts/user/{userId}")
    public ResponseEntity<List<Post>> findUserPosts(@PathVariable Integer userId) throws Exception {
        List<Post> posts = postService.findPostsByUserId(userId);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }
    @GetMapping("/posts")
    public ResponseEntity<List<Post>> findAllPosts() throws Exception {
        List<Post> posts = postService.findAllPost();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }
    @PutMapping("/posts/{postId}/users/{userId}")
    public ResponseEntity<Post> savePostHandler(@PathVariable Integer postId, @PathVariable Integer userId) throws Exception{
        Post post = postService.savePost(postId, userId);
        return new ResponseEntity<>(post, HttpStatus.ACCEPTED);
    }
    @PutMapping("/posts/{postId}/likes/users/{userId}")
    public ResponseEntity<Post> savedPostHandler(@PathVariable Integer postId, @PathVariable Integer userId) throws Exception{
        Post post = postService.likePost(postId, userId);
        return new ResponseEntity<>(post, HttpStatus.ACCEPTED);
    }
}
