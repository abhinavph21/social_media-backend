package com.example.SocialMediabackend.service;

import com.example.SocialMediabackend.model.Chat;
import com.example.SocialMediabackend.model.Message;
import com.example.SocialMediabackend.model.User;
import com.example.SocialMediabackend.repository.ChatRepository;
import com.example.SocialMediabackend.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class MessageServiceImplementation implements MessageService{
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private ChatService chatService;
    @Autowired
    private ChatRepository chatRepository;
    @Override
    public Message createMessage(User user, Integer chatId, Message inputMsg) throws Exception {
        try {
            Message newMessage = new Message();
            Chat chat = chatService.findChatById(chatId);
//            Chat newChat = new Chat();
            newMessage.setChat(chat);
            newMessage.setParentChatId(chat.getId());
            newMessage.setContent(inputMsg.getContent());
            newMessage.setImage(inputMsg.getImage());
            newMessage.setUser(user);
            newMessage.setTimestamp(LocalDateTime.now());
//            System.out.println(newMessage.getChat()+ " "+newMessage.getUser());
            Message savedMessage =  messageRepository.saveAndFlush(newMessage);
            System.out.println(savedMessage);
            List<Message> chatMessages = chat.getMessages();
            if(chatMessages==null){
                chatMessages=new ArrayList<>();
                chatMessages.add(savedMessage);
                chat.setMessages(chatMessages);
            } else {
                chatMessages.add(savedMessage);
            }
            chatRepository.saveAndFlush(chat);
            return savedMessage;
        }catch (Exception exception){
            throw new Exception(exception.toString());
        }
    }

    @Override
    public List<Message> findChatMessages(Integer chatId) throws Exception {
        Chat chat = chatService.findChatById(chatId);
        return messageRepository.findByChatId(chatId);
    }
}
