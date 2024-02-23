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
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Id;

    private String chat_name;

    private String chat_image;

    @ManyToMany
    private List<User> users;

    private LocalDateTime timestamp;
}
