package com.example.SocialMediabackend.controller;

import com.example.SocialMediabackend.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {
    @GetMapping("/users")
    public List<User> getAllUsers(){
        List<User> users = new ArrayList<>();
        User user1 = new User(1, "abhinav", "pass", "ap.abhinav16", "abhinav", "pharswan", "78555");
        User user2 = new User(2, "abhinav", "pass", "ap.abhinav16", "abhinav", "pharswan", "78555");
        users.add(user1);
        users.add(user2);
        return users;
    }
    @GetMapping("/users/{userId}")
    public User getUserbyId(@PathVariable Integer userId){
        User user = new User(1, "abhinav", "pass", "ap.abhinav16", "abhinav", "pharswan", "78555");
        user.setId(userId);
//        User user2 = new User(2, "abhinav", "pass", "ap.abhinav16", "abhinav", "pharswan", "78555");
        return user;
    }
    @PostMapping("/users")
    public User createUser(@RequestBody User inputUser){
        User outputUser = new User();
        outputUser.setId(inputUser.getId());
        outputUser.setFirstName(inputUser.getFirstName());
        outputUser.setLastName(inputUser.getLastName());
        outputUser.setEmail(inputUser.getEmail());
        outputUser.setPassword(inputUser.getPassword());
        return outputUser;
    }
    @PutMapping("/users")
    public User updateUser( @RequestBody User user) {
        User newUser = new User(1, "abhinav", "pass", "ap.abhinav16", "abhinav", "pharswan", "78555");
        if(user.getFirstName()!=null)
            newUser.setFirstName(user.getFirstName());
        if(user.getLastName()!=null)
            newUser.setLastName(user.getLastName());
        if(user.getEmail()!=null)
            newUser.setEmail(user.getEmail());
        if(user.getPassword()!=null)
            newUser.setPassword(user.getPassword());
        return newUser;
    }
    @DeleteMapping("/users/{userId}")
    public String deleteUserById(@PathVariable Integer userId){
        return "deleted user with id " + userId;
    }
}
