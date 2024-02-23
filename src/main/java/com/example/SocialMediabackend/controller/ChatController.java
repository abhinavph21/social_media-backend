package com.example.SocialMediabackend.controller;

import com.example.SocialMediabackend.model.Chat;
import com.example.SocialMediabackend.model.User;
import com.example.SocialMediabackend.request.CreateChatRequest;
import com.example.SocialMediabackend.service.ChatService;
import com.example.SocialMediabackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class ChatController {
    @Autowired
    private ChatService chatService;
    @Autowired
    private UserService userService;
    @PostMapping("/api/chats")
    public Chat createChat(@RequestHeader("Authorization") String jwt, @RequestBody CreateChatRequest req) throws Exception {
        try {
            User reqUser = userService.findUserByJwt(jwt);
            User user2 = userService.findUserById(req.getUserId());
            Chat newChat = chatService.createChat(reqUser, user2);
            return newChat;
        } catch (Exception exception){
            throw new Exception(exception.toString());
        }
    }
    @GetMapping("/api/chats")
    public List<Chat> getChats(@RequestHeader("Authorization") String jwt) throws Exception {
        try {
            User reqUser = userService.findUserByJwt(jwt);
            List<Chat> chats = chatService.findUserChats(reqUser.getId());
            return chats;
        } catch (Exception exception){
            throw new Exception(exception.toString());
        }
    }
}
