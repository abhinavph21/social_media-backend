package com.example.SocialMediabackend.controller;

import com.example.SocialMediabackend.model.Story;
import com.example.SocialMediabackend.model.User;
import com.example.SocialMediabackend.service.StoryService;
import com.example.SocialMediabackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StoryController {
    @Autowired
    private StoryService storyService;
    @Autowired
    private UserService userService;
    @PostMapping("/api/stories")
    public Story createStory(@RequestBody Story story, @RequestHeader("Authorization") String jwt) throws Exception {
        User reqUser = userService.findUserByJwt(jwt);
        Story createdStory = storyService.createStory(story, reqUser);
        return createdStory;
    }
    @GetMapping("/api/users/{userId}/stories")
    public List<Story> findUserStories(@PathVariable Integer userId, @RequestHeader("Authorization") String jwt) throws Exception {
        User reqUser = userService.findUserByJwt(jwt);
        List<Story> stories = storyService.findStoryByUserId(userId);
        return stories;
    }
}
