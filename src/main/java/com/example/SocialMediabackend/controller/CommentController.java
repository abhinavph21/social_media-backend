package com.example.SocialMediabackend.controller;

import com.example.SocialMediabackend.model.Comment;
import com.example.SocialMediabackend.model.User;
import com.example.SocialMediabackend.service.CommentService;
import com.example.SocialMediabackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;
    public Comment createComment(@RequestBody Comment inputComment, @RequestHeader("Authorization") String jwt){
        User user = userService.findUserBy
        return null;
    }
}
