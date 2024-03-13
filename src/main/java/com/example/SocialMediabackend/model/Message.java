package com.example.SocialMediabackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "messages")
public class Message {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String content;

    private String image;

    @ManyToOne
    private User user;

    @Column(name = "parentChatId")
    private Integer parentChatId;

//    since message will have chat which will have message which will have chat
//    avoid nesting error
    @JsonIgnore
    @ManyToOne
    private Chat chat;

    private LocalDateTime timestamp;


}
