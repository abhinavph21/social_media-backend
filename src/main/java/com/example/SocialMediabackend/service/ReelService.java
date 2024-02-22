package com.example.SocialMediabackend.service;

import com.example.SocialMediabackend.model.Reel;
import com.example.SocialMediabackend.model.User;

import java.util.List;

public interface ReelService {
    public Reel createReel(Reel reel, User user);

    public List<Reel> findAllReels();

    public List<Reel> findUsersReel(Integer userId) throws Exception;
}
