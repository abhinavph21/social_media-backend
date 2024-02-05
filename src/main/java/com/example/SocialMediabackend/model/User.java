package com.example.SocialMediabackend.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    private Integer id;

    private String username;

    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String mobile;

    String gender;

    private List<Integer> followers;

    private List<Integer> following;

    @ManyToMany
    private Set<Post> savedPosts;

}
