package com.example.SocialMediabackend.controller;

import com.example.SocialMediabackend.model.User;
import com.example.SocialMediabackend.repository.UserRepository;
import com.example.SocialMediabackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.SocialMediabackend.exceptions.UserException;
import java.util.*;

@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;
    @GetMapping("api/users")
    public List<User> getAllUsers(){
        List<User> users = null;
        try{
            users = userRepository.findAll();
        } catch (Exception exception){
            System.out.println(exception.toString());
        }
        return users;
    }
    @GetMapping("/api/users/{userId}")
    public User getUserById(@PathVariable Integer userId) throws UserException {
        try {
            User foundUser = userService.findUserById(userId);
            return foundUser;
        } catch(Exception exception) {
            throw new UserException(exception.toString());
        }
    }
    @PostMapping("/api/users")
    public User createUser(@RequestBody User inputUser) throws UserException {
        try {
            User user = userService.registerUser(inputUser);
            return user;
        }
        catch (Exception exception){
            throw new UserException(exception.toString());
        }
    }
    @PutMapping("/api/users")
    public User updateUser(@RequestHeader("Authorization")String jwt, @RequestBody User user) throws UserException {
        try {
            User reqUser = userService.findUserByJwt(jwt);
            User updatedUser = userService.updateUserById(user, reqUser.getId());
            return updatedUser;
        }catch (Exception exception){
            System.out.println(exception.toString());
            throw new UserException(exception.toString());
        }
    }
    @PutMapping("/api/users/follow/{userId2}")
    public User followUser(@RequestHeader("Authorization")String jwt,  @PathVariable Integer userId2) throws UserException {
        try {
            User reqUser = userService.findUserByJwt(jwt);
            User userWhoFollows = userService.followUserById(reqUser.getId(), userId2);
            return userWhoFollows;
        }catch (Exception exception){
            System.out.println(exception.toString());
            throw new UserException(exception.toString());
        }
    }
    @GetMapping("/api/users/search")
    public Set<User> getUserByQuery(@RequestParam("query") String query) throws UserException {
        try {
            Set<User> foundUsers = userService.findUserByQuery(query);
            return foundUsers;
        } catch(Exception exception) {
            throw new UserException(exception.toString());
        }
    }
    @GetMapping("/api/users/profile")
    public User getUserFromToken(@RequestHeader("Authorization") String jwt) throws  UserException{
        try {
            User user = userService.findUserByJwt(jwt);
            user.setPassword(null);
            return user;
        } catch (Exception exception){
            throw new UserException(exception.toString());
        }
    }
}
