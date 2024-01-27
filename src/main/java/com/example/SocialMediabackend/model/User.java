package com.example.SocialMediabackend.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class User {

    private Integer id;
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String mobile;



}
