package com.example.SocialMediabackend.service;

import com.example.SocialMediabackend.model.Chat;
import com.example.SocialMediabackend.model.Message;
import com.example.SocialMediabackend.model.User;

import java.util.List;

public interface MessageService {
    public Message createMessage(User user, Integer chatId, Message inputMsg) throws Exception;

    public List<Message> findChatMessages(Integer chatId) throws Exception;
}
