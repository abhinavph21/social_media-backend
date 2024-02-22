package com.example.SocialMediabackend.service;

import com.example.SocialMediabackend.model.Story;
import com.example.SocialMediabackend.model.User;

import java.util.List;

public interface StoryService {
    public Story createStory(Story story, User user);

    public List<Story> findStoryByUserId(Integer userId) throws Exception;
}
