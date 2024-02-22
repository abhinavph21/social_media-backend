package com.example.SocialMediabackend.service;

import com.example.SocialMediabackend.model.Story;
import com.example.SocialMediabackend.model.User;
import com.example.SocialMediabackend.repository.StoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class StoryServiceImplementation implements StoryService{
    @Autowired
    private StoryRepository storyRepository;
    @Autowired
    private UserService userService;
    @Override
    public Story createStory(Story story, User user) {
        Story newStory = new Story();
        newStory.setCaption(story.getCaption());
        newStory.setImage(story.getImage());
        newStory.setUser(user);
        newStory.setTimestamp(LocalDateTime.now());
        return storyRepository.saveAndFlush(newStory);
    }

    @Override
    public List<Story> findStoryByUserId(Integer userId) throws Exception {
        User foundUser = userService.findUserById(userId);
        return storyRepository.findByUserId(foundUser.getId());
    }
}
