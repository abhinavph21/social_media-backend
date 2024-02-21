package com.example.SocialMediabackend.repository;

import com.example.SocialMediabackend.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
}
