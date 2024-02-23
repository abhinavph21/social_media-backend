package com.example.SocialMediabackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "chats")
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Id;

    private String chat_name;

    private String chat_image;

    @ManyToMany
    private List<User> users;

    private LocalDateTime timestamp;

    // so that extra table isn't created for message (message_chat)
    @OneToMany(mappedBy = "chat")
    private List<Message> messages;

}
