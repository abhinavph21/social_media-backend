package com.example.SocialMediabackend.service;

import com.example.SocialMediabackend.model.Chat;
import com.example.SocialMediabackend.model.User;

import java.util.List;

public interface ChatService {
    public Chat createChat(User reqUser, User user2) ;

    public Chat findChatById(Integer chatId) throws Exception;

    public List<Chat> findUserChats(Integer userId);
}
