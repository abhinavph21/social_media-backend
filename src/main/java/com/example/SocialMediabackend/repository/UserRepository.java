package com.example.SocialMediabackend.repository;

import com.example.SocialMediabackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
