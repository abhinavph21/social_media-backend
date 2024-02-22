package com.example.SocialMediabackend.service;

import com.example.SocialMediabackend.model.Reel;
import com.example.SocialMediabackend.model.User;
import com.example.SocialMediabackend.repository.ReelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ReelServiceImplementation implements ReelService{
    @Autowired
    private ReelRepository reelRepository;

    @Autowired
    private UserService userService;

    @Override
    public Reel createReel(Reel reel, User user) {
        Reel newReel = new Reel();
        newReel.setUser(user);
        newReel.setTitle(reel.getTitle());
        newReel.setVideo(reel.getVideo());

        return reelRepository.saveAndFlush(newReel);
    }

    @Override
    public List<Reel> findAllReels() {
        return reelRepository.findAll();
    }

    @Override
    public List<Reel> findUsersReel(Integer userId) throws Exception {
        try {
            User foundUser = userService.findUserById(userId);
            return reelRepository.findByUserId(foundUser.getId());
        } catch (Exception exception){
            throw new Exception(exception.toString());
        }
    }
}
