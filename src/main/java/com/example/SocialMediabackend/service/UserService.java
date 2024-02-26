package com.example.SocialMediabackend.service;

import com.example.SocialMediabackend.exceptions.UserException;
import com.example.SocialMediabackend.model.User;

import java.util.List;
import java.util.Set;

public interface UserService {
    public User registerUser(User user) throws Exception;

    public User findUserById(Integer id) throws UserException;

    public User findUserByEmail(String email) throws Exception;

    public List<User> findUsersByUserIds(List<Integer> userIds);

    public Set<User> findUserByQuery(String query) throws Exception;

    public User findUserByJwt(String jwt) throws Exception;

    public User followUserById(Integer reqUserId, Integer followUserId) throws Exception;

    public User updateUserById(User user, Integer userId) throws Exception;
}
