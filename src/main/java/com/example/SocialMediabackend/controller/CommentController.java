package com.example.SocialMediabackend.controller;

import com.example.SocialMediabackend.model.Comment;
import com.example.SocialMediabackend.model.User;
import com.example.SocialMediabackend.service.CommentService;
import com.example.SocialMediabackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;
    @PostMapping("/api/posts/{postId}/comments")
    public Comment createComment(@RequestHeader("Authorization") String jwt, @RequestBody Comment inputComment, @PathVariable Integer postId) throws Exception {
        User user = userService.findUserByJwt(jwt);
        Comment createdComment = commentService.createComment(inputComment,
                postId, user.getId());
        return createdComment;
    }
    @PutMapping("/api/comments/{commentId}/like")
    public Comment likeComment(@RequestHeader("Authorization") String jwt, @PathVariable Integer commentId) throws Exception {
        User reqUser = userService.findUserByJwt(jwt);
        Comment likedComment = commentService.likeComment(commentId, reqUser.getId());
        return likedComment;
    }
}
