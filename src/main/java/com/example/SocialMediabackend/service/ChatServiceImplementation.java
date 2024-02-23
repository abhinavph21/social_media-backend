package com.example.SocialMediabackend.service;

import com.example.SocialMediabackend.model.Chat;
import com.example.SocialMediabackend.model.User;
import com.example.SocialMediabackend.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ChatServiceImplementation implements ChatService{
    @Autowired
    private ChatRepository chatRepository;
    @Override
    public Chat createChat(User reqUser, User user2) {
        Chat existingChat = chatRepository.findChatByUsers(reqUser, user2);
        if(existingChat!=null)
            return existingChat;
        Chat newChat = new Chat();
        List<User> chatUsers = new ArrayList<>();
        chatUsers.add(reqUser);
        chatUsers.add(user2);
        newChat.setUsers(chatUsers);
        newChat.setTimestamp(LocalDateTime.now());
        return chatRepository.saveAndFlush(newChat);
    }

    @Override
    public Chat findChatById(Integer chatId) throws Exception {
        Optional<Chat> optChat = chatRepository.findById(chatId);
        if(optChat.isEmpty())
            throw new Exception("chat not found with id "+ chatId);
        return optChat.get();
    }

    @Override
    public List<Chat> findUserChats(Integer userId) {
        return chatRepository.findByUserId(userId);
    }
}
