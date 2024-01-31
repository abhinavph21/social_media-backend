package com.example.SocialMediabackend.service;

import com.example.SocialMediabackend.model.User;
import com.example.SocialMediabackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
@Component
public class UserServiceImplementation implements UserService{
    @Autowired
    UserRepository userRepository;
    @Override
    public User registerUser(User inputUser) throws Exception {
        User newUser = new User();
        newUser.setId(inputUser.getId());
        newUser.setFirstName(inputUser.getFirstName());
        newUser.setLastName(inputUser.getLastName());
        newUser.setEmail(inputUser.getEmail());
        newUser.setPassword(inputUser.getPassword());
        try {
            User savedUser=null;
            savedUser= userRepository.saveAndFlush(newUser);
            return savedUser;
        } catch (Exception exception){
            System.out.println(exception.toString());
            throw new Exception(exception.toString());
        }
    }

    @Override
    public User findUserById(Integer id) throws Exception {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            return user.get();
        }
        throw new Exception("user not found");
    }

    @Override
    public User findUserByEmail(String email) throws Exception{
        Optional<User> user= userRepository.findByEmail(email);
        if(user.isPresent()){
            return user.get();
        }
        throw new Exception("user not found with email "+email);
    }

    @Override
    public List<User> findUsersByUserIds(List<Integer> userIds) {
        return null;
    }

    @Override
    public Set<User> findUserByQuery(String query) throws Exception {
        try{
            Set<User> users = userRepository.findByQuery(query);
            return users;
        } catch (Exception exception){
            System.out.println(exception.toString());
            throw new Exception(exception.toString());
        }
    }
    public User followUserById(Integer userId1, Integer userId2) throws Exception {
        System.out.println(userId1+ " "+userId2);
        try{
            User userToFollow = findUserById(userId2);
            User userWhoFollows = findUserById(userId1);

            List<Integer> following = userWhoFollows.getFollowing();
            List<Integer> followers = userToFollow.getFollowers();

            if(following==null) {
                following = new ArrayList<>();
                userWhoFollows.setFollowing(following);
            }
            if(followers==null) {
                followers = new ArrayList<>();
                userToFollow.setFollowers(followers);
            }

            following.add(userId2);
            followers.add(userId1);

            System.out.println(userId1+ " following " + userWhoFollows.getFollowing());

            userRepository.saveAndFlush(userToFollow);
            User updatedUserWhoFollows = userRepository.saveAndFlush(userWhoFollows);
            System.out.println(updatedUserWhoFollows+ " user from db");
            return updatedUserWhoFollows;
        } catch (Exception exception){
            System.out.println("exception in following");
            throw new Exception(exception.toString());
        }
    }
    public User updateUserById(User user, Integer userId) throws Exception {
        User updatedUser = null;
        try {
            User foundUser = findUserById(userId);
            if(user.getFirstName()!=null)
                foundUser.setFirstName(user.getFirstName());
            if(user.getLastName()!=null)
                foundUser.setLastName(user.getLastName());
            if(user.getEmail()!=null)
                foundUser.setEmail(user.getEmail());
            if(user.getPassword()!=null)
                foundUser.setPassword(user.getPassword());
            updatedUser = userRepository.saveAndFlush(foundUser);
            return updatedUser;
        } catch(Exception exception){
            throw new Exception(exception.toString());
        }
    }
}
