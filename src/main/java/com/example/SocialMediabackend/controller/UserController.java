package com.example.SocialMediabackend.controller;

import com.example.SocialMediabackend.model.User;
import com.example.SocialMediabackend.repository.UserRepository;
import com.example.SocialMediabackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;
    @GetMapping("/users")
    public List<User> getAllUsers(){
        List<User> users = null;
        try{
            users = userRepository.findAll();
        } catch (Exception exception){
            System.out.println(exception.toString());
        }
        return users;
    }
    @GetMapping("/users/{userId}")
    public User getUserById(@PathVariable Integer userId) throws Exception {
        try {
            User foundUser = userService.findUserById(userId);
            return foundUser;
        } catch(Exception exception) {
            throw new Exception(exception.toString());
        }
    }
    @PostMapping("/users")
    public User createUser(@RequestBody User inputUser) throws Exception {
        try {
            User user = userService.registerUser(inputUser);
            return user;
        }
        catch (Exception exception){
            throw new Exception(exception.toString());
        }
    }
    @PutMapping("/users/{userId}")
    public User updateUser( @RequestBody User user, @PathVariable Integer userId) throws Exception {
        try {
            User updatedUser = userService.updateUserById(user, userId);
            return updatedUser;
        }catch (Exception exception){
            System.out.println(exception.toString());
            throw new Exception(exception.toString());
        }
    }
    @PutMapping("/users/follow/{userId1}/{userId2}")
    public User followUser( @PathVariable Integer userId1, @PathVariable Integer userId2) throws Exception {
        try {
            User userWhoFollows = userService.followUserById(userId1, userId2);
            return userWhoFollows;
        }catch (Exception exception){
            System.out.println(exception.toString());
            throw new Exception(exception.toString());
        }
    }
    @GetMapping("/users/search")
    public Set<User> getUserByQuery(@RequestParam("query") String query) throws Exception {
        try {
            Set<User> foundUsers = userService.findUserByQuery(query);
            return foundUsers;
        } catch(Exception exception) {
            throw new Exception(exception.toString());
        }
    }
}
