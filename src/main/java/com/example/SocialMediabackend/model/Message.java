package com.example.SocialMediabackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "messages")
public class Message {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Id;

    private String content;

    private String image;

    @ManyToOne
    private User user;

//    since message will have chat which will have message which will have chat
//    avoid nesting error
    @JsonIgnore
    @ManyToOne
    private Chat chat;

    private LocalDateTime timestamp;


}
