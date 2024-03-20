package com.example.SocialMediabackend.service;

import com.example.SocialMediabackend.config.JwtProvider;
import com.example.SocialMediabackend.exceptions.UserException;
import com.example.SocialMediabackend.model.User;
import com.example.SocialMediabackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImplementation implements UserService{
    @Autowired
    UserRepository userRepository;
    @Override
    public User registerUser(User inputUser) throws Exception {
        User newUser = new User();
        newUser.setUsername(inputUser.getUsername());
        newUser.setGender(inputUser.getGender());
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
    public User findUserById(Integer id) throws UserException {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            return user.get();
        }
        throw new UserException("user not found");
    }

    @Override
    public User findUserByEmail(String email) throws UserException{
        Optional<User> user= userRepository.findByEmail(email);
        if(user.isPresent()){
            return user.get();
        }
        throw new UserException("user not found with email "+email);
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

    @Override
    public User findUserByJwt(String jwt) throws UserException{
        String email = JwtProvider.getEmailFromJwtToken(jwt);
        Optional<User> user=userRepository.findByEmail(email);
        if(user.isPresent())
            return user.get();
        throw new UserException("user not found with given token");
    }

    public User followUserById(Integer reqUserId, Integer userId2) throws Exception {
        System.out.println(reqUserId+ " "+userId2);
        try{
            User userWhoFollows = findUserById(reqUserId);
            User userToFollow = findUserById(userId2);

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
            followers.add(reqUserId);

            System.out.println(reqUserId+ " following " + userWhoFollows.getFollowing());

            User updatedFollowedUser = userRepository.saveAndFlush(userToFollow);
            userRepository.saveAndFlush(userWhoFollows);
            System.out.println(updatedFollowedUser+ " user from db");
            return updatedFollowedUser;
        } catch (Exception exception){
            System.out.println("exception in following");
            throw new Exception(exception.toString());
        }
    }
    public User updateUserById(User user, Integer userId) throws Exception {
        User updatedUser = null;
        System.out.println(user);
        try {
            User foundUser = findUserById(userId);
            if(user.getFirstName()!=null)
                foundUser.setFirstName(user.getFirstName());
            if(user.getLastName()!=null)
                foundUser.setLastName(user.getLastName());
            if(user.getEmail()!=null)
                foundUser.setEmail(user.getEmail());
            if(user.getImage()!=null)
                foundUser.setImage(user.getImage());
            if (user.getBio()!=null)
                foundUser.setBio(user.getBio());
            updatedUser = userRepository.saveAndFlush(foundUser);
            System.out.println(updatedUser);
            return updatedUser;
        } catch(Exception exception){
            throw new Exception(exception.toString());
        }
    }
}
