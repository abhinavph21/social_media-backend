package com.example.SocialMediabackend.controller;

import com.example.SocialMediabackend.model.Reel;
import com.example.SocialMediabackend.model.User;
import com.example.SocialMediabackend.service.ReelService;
import com.example.SocialMediabackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReelController {
    @Autowired
    private ReelService reelService;
    @Autowired
    private UserService userService;
    @PostMapping("/api/reels")
    public Reel createReel(@RequestHeader("Authorization")String jwt, @RequestBody Reel reel) throws Exception {
        try {
            User reqUser=userService.findUserByJwt(jwt);
            Reel createdReel = reelService.createReel(reel, reqUser);
            return createdReel;
        } catch (Exception exception){
            throw new Exception(exception.toString());
        }
    }
    @GetMapping("/api/reels")
    public List<Reel> findAllReels() throws Exception{
        try {
            List<Reel> foundReels = reelService.findAllReels();
            return foundReels;
        } catch (Exception exception){
            throw new Exception(exception.toString());
        }
    }
    @GetMapping("/api/users/{userId}/reels")
    public List<Reel> findUsersReels(@PathVariable Integer userId) throws Exception{
        try {
            List<Reel> foundReels = reelService.findUsersReel(userId);
            return foundReels;
        } catch (Exception exception){
            throw new Exception(exception.toString());
        }
    }
}
