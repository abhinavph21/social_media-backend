package com.example.SocialMediabackend.controller;

import com.example.SocialMediabackend.model.User;
import com.example.SocialMediabackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;
    @GetMapping("/users")
    public List<User> getAllUsers(){
//        List<User> users = new ArrayList<>();
//        User user1 = new User(1, "abhinav", "pass", "ap.abhinav16", "abhinav", "pharswan", "78555");
//        User user2 = new User(2, "abhinav", "pass", "ap.abhinav16", "abhinav", "pharswan", "78555");
//        users.add(user1);
//        users.add(user2);
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
//        User user = new User(1, "abhinav", "pass", "ap.abhinav16", "abhinav", "pharswan", "78555");
//        user.setId(userId);
//        User user2 = new User(2, "abhinav", "pass", "ap.abhinav16", "abhinav", "pharswan", "78555");
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()){
            return user.get();
        }
        throw new Exception("user not found");
    }
    @PostMapping("/users")
    public User createUser(@RequestBody User inputUser){
        User newUser = new User();
        newUser.setId(inputUser.getId());
        newUser.setFirstName(inputUser.getFirstName());
        newUser.setLastName(inputUser.getLastName());
        newUser.setEmail(inputUser.getEmail());
        newUser.setPassword(inputUser.getPassword());
        User savedUser=null;
        try {
            savedUser= userRepository.saveAndFlush(newUser);
        } catch (Exception exception){
            System.out.println(exception.toString());
        }
        return savedUser;
    }
    @PutMapping("/users/{userId}")
    public User updateUser( @RequestBody User user, @PathVariable Integer userId) throws Exception {
//        User newUser = new User(1, "abhinav", "pass", "ap.abhinav16", "abhinav", "pharswan", "78555");
        User newUser = null;
        User updatedUser = null;
        Optional<User> foundUser = userRepository.findById(userId);
        if(foundUser.isPresent()){
            newUser = foundUser.get();
            if(user.getFirstName()!=null)
                newUser.setFirstName(user.getFirstName());
            if(user.getLastName()!=null)
                newUser.setLastName(user.getLastName());
            if(user.getEmail()!=null)
                newUser.setEmail(user.getEmail());
            if(user.getPassword()!=null)
                newUser.setPassword(user.getPassword());
            updatedUser = userRepository.saveAndFlush(newUser);
            return updatedUser;
        }
        throw new Exception("user not found");
    }
    @DeleteMapping("/users/{userId}")
    public String deleteUserById(@PathVariable Integer userId){
        Optional<User> userToBeDeleted = userRepository.findById(userId);
        if(userToBeDeleted.isEmpty())
            return "user not found";
        return "deleted user with id "+userId;
    }
}
