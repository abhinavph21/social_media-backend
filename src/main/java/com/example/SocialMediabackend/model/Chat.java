package com.example.SocialMediabackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "chats")
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String chat_name;

    private String chat_image;

    @ManyToMany
    private List<User> users;

    private LocalDateTime timestamp;

    // so that extra table isn't created for message (message_chat)
    @OneToMany(mappedBy = "chat")
    private List<Message> messages;

}
