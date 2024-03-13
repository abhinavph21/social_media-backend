package com.example.SocialMediabackend.controller;

import com.example.SocialMediabackend.model.Chat;
import com.example.SocialMediabackend.model.Message;
import com.example.SocialMediabackend.model.User;
import com.example.SocialMediabackend.service.MessageService;
import com.example.SocialMediabackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MessageController {
    @Autowired
    private MessageService messageService;
    @Autowired
    private UserService userService;

    @PostMapping("/api/chats/{chatId}/messages")
    public Message createMessage(@RequestHeader("Authorization") String jwt,
                                 @PathVariable Integer chatId,
                                 @RequestBody Message inputMessage) throws Exception {
        User reqUser = userService.findUserByJwt(jwt);
        Message savedMessage = messageService.createMessage(reqUser, chatId, inputMessage );
        return savedMessage;
    }
    @GetMapping("/api/chats/{chatId}/messages")
    public List<Message> getChatMessages(@RequestHeader("Authorization") String jwt,
                                         @PathVariable Integer chatId) throws Exception {
        User reqUser = userService.findUserByJwt(jwt);
        List<Message> messages = messageService.findChatMessages(chatId );
        return messages;
    }

}
