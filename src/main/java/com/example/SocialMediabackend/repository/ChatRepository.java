package com.example.SocialMediabackend.repository;

import com.example.SocialMediabackend.model.Chat;
import com.example.SocialMediabackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, Integer> {
    @Query("SELECT c FROM Chat c JOIN c.users u WHERE u.id=:userId")
    public List<Chat> findByUserId(Integer userId);
    @Query("SELECT c from Chat c WHERE :user1 Member of c.users AND :user2 Member of c.users")
    public Chat findChatByUsers(@Param("user1")User user1, @Param("user2") User user2);

}
