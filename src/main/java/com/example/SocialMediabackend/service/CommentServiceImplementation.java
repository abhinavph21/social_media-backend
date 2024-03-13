package com.example.SocialMediabackend.service;

import com.example.SocialMediabackend.model.Comment;
import com.example.SocialMediabackend.model.Post;
import com.example.SocialMediabackend.model.User;
import com.example.SocialMediabackend.repository.CommentRepository;
import com.example.SocialMediabackend.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
@Service
public class CommentServiceImplementation implements CommentService{
    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostRepository postRepository;
    @Override
    public Comment createComment(Comment comment, Integer postId, Integer userId) throws Exception {
        try {
            User user = userService.findUserById(userId);
            Post post = postService.findPostById(postId);

            comment.setUser(user);
            comment.setContent(comment.getContent());
            comment.setCreatedAt(LocalDateTime.now());
            comment.setParentPostId(post.getId());
            Comment savedComment = commentRepository.saveAndFlush(comment);
            List<Comment> comments = post.getComments();
            if(comments==null)
                comments = new ArrayList<>();
            comments.add(savedComment);
            postRepository.saveAndFlush(post);
            return savedComment;
        } catch (Exception exception){
            throw new Exception(exception.toString());
        }
    }

    @Override
    public Comment findCommentById(Integer commentId) throws Exception {
        Optional<Comment> comment = commentRepository.findById(commentId);
        if(comment.isEmpty())
            throw new Exception("comment doesn't exist with given id");
        return comment.get();
    }

    @Override
    public Comment likeComment(Integer commentId, Integer userId) throws Exception{
        try {
            Comment comment = findCommentById(commentId);
            User user = userService.findUserById(userId);
            Set<User> likedUsers = comment.getLiked();
            if(likedUsers==null) {
                likedUsers = new HashSet<>();
                likedUsers.add(user);
                comment.setLiked(likedUsers);
            }
            else if(!likedUsers.contains(user))
                likedUsers.add(user);
            else
                likedUsers.remove(user);
            return commentRepository.saveAndFlush(comment);
        }catch (Exception exception) {
            throw new Exception(exception.toString());
        }
    }
}
