package com.example.SocialMediabackend.controller;

import com.example.SocialMediabackend.model.Post;
import com.example.SocialMediabackend.model.User;
import com.example.SocialMediabackend.response.ApiResponse;
import com.example.SocialMediabackend.service.PostService;
import com.example.SocialMediabackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class PostController {
    @Autowired
    PostService postService;
    @Autowired
    UserService userService;
    @PostMapping("/api/posts")
    public ResponseEntity<Post> createPost(@RequestHeader("Authorization")String jwt, @RequestBody Post post) throws Exception {
        try {
            User reqUser = userService.findUserByJwt(jwt);
            Post newPost = postService.createPost(post, reqUser.getId());
            return new ResponseEntity<>(newPost, HttpStatus.OK);
        } catch (Exception exception){
            System.out.println(exception.toString());
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
    @DeleteMapping("/api/posts/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@RequestHeader("Authorization")String jwt, @PathVariable Integer postId) throws Exception{
        try {
            User reqUser = userService.findUserByJwt(jwt);
            String postMessage = postService.deletePost(postId, reqUser.getId());
            ApiResponse res = new ApiResponse(postMessage, true);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception exception){
            return new ResponseEntity<>(new ApiResponse(exception.toString(), false), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
    @GetMapping("/api/posts/{postId}")
    public ResponseEntity<Post> findPostByIdHandler(@PathVariable Integer postId) throws Exception{
        Post post = postService.findPostById(postId);
        return new ResponseEntity<>(post, HttpStatus.ACCEPTED);
    }
    @GetMapping("/api/users/{userId}/posts")
    public ResponseEntity<List<Post>> findUserPosts(@PathVariable Integer userId) throws Exception {
        List<Post> posts = postService.findPostsByUserId(userId);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }
    @GetMapping("/api/posts")
    public ResponseEntity<List<Post>> findAllPosts() throws Exception {
        List<Post> posts = postService.findAllPost();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }
    @PutMapping("/api/posts/{postId}/save")
    public ResponseEntity<Post> savePostHandler(@RequestHeader("Authorization")String jwt,@PathVariable Integer postId) throws Exception{
        try {
            User reqUser = userService.findUserByJwt(jwt);
            Post post = postService.savePost(postId, reqUser.getId());
            return new ResponseEntity<>(post, HttpStatus.ACCEPTED);
        } catch (Exception exception){
            throw new Exception(exception.toString());
        }
    }
    @PutMapping("/api/posts/{postId}/like")
    public ResponseEntity<Post> likePost(@RequestHeader("Authorization")String jwt,@PathVariable Integer postId) throws Exception{
        try {
            User reqUser = userService.findUserByJwt(jwt);
            Post post = postService.likePost(postId, reqUser.getId());
            return new ResponseEntity<>(post, HttpStatus.ACCEPTED);
        } catch (Exception exception){
            throw new Exception(exception.toString());
        }
    }
}
