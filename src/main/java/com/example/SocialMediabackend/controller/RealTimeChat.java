package com.example.SocialMediabackend.controller;

import com.example.SocialMediabackend.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RealTimeChat {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
    @MessageMapping("/chat/{chatId}")
    public Message sendToUser(@Payload Message message, @DestinationVariable String chatId){
        System.out.println(message);
        simpMessagingTemplate.convertAndSendToUser(chatId, "/private", message);
        return message;
    }
}
