package com.example.SocialMediabackend.repository;

import com.example.SocialMediabackend.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {
    @Query("SELECT p from Post p WHERE p.user.id=:userId")
    List<Post> findPostsWithUserId(Integer userId);
}
