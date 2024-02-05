package com.example.SocialMediabackend.service;

import com.example.SocialMediabackend.model.Post;

import java.util.List;

public interface PostService {
    public Post createPost(Post post, Integer userId) throws Exception;

    public String deletePost(Integer postId, Integer userId) throws Exception;

    public List<Post> findPostsByUserId(Integer userId) throws Exception;

    public List<Post> findAllPost() throws Exception;
    public Post likePost(Integer postId ,Integer userId) throws Exception;

    public Post savePost(Integer postId,Integer userId) throws Exception;


    public Post findPostById(Integer postId) throws Exception;
}