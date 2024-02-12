package com.example.SocialMediabackend.service;

import com.example.SocialMediabackend.model.Post;
import com.example.SocialMediabackend.model.User;
import com.example.SocialMediabackend.repository.PostRepository;
import com.example.SocialMediabackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PostServiceImplementation implements PostService{
    @Autowired
    PostRepository postRepository;
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @Override
    public Post createPost(Post post, Integer userId) throws Exception {
        try {
            User foundUser = userService.findUserById(userId);
            Post newPost = new Post();
            newPost.setCaption(post.getCaption());
            newPost.setVideo(post.getVideo());
            newPost.setImage(post.getImage());
            newPost.setCreatedAt(LocalDateTime.now());
            newPost.setUser(foundUser);
            Post savedPost = postRepository.saveAndFlush(newPost);
            return savedPost;
        }catch (Exception exception){
            throw new Exception(exception.toString());
        }
    }

    @Override
    public String deletePost(Integer postId, Integer userId) throws Exception {
        try{
            Post post = findPostById(postId);
            User user = userService.findUserById(userId);
            if(post.getUser().getId()!=user.getId()){
                throw new Exception("cannot delete another user's post");
            }
            postRepository.delete(post);
            return "post with id " + postId+ " deleted successfully";
        } catch (Exception exception){
            throw new Exception(exception.toString());
        }
    }

    @Override
    public List<Post> findPostsByUserId(Integer userId) throws Exception {
        return postRepository.findPostsWithUserId(userId);
    }

    @Override
    public List<Post> findAllPost() throws Exception {
        List<Post> posts = postRepository.findAll();
        return posts;
    }

    @Override
    public Post likePost(Integer postId, Integer userId) throws Exception {
        try{
            Post post = findPostById(postId);
            User user = userService.findUserById(userId);
            Set<User> likedUsers;
            likedUsers=post.getLiked();
            if (likedUsers==null){
                likedUsers=new HashSet<>();
                likedUsers.add(user);
                post.setLiked(likedUsers);
            }
            else if(likedUsers.contains(user))
                likedUsers.remove(user);
            else
                likedUsers.add(user);
            Post updatedLikedPost = postRepository.save(post);
            return updatedLikedPost;
        }catch (Exception exception){
            throw new Exception(exception.toString());
        }
    }

    @Override
    public Post savePost(Integer postId, Integer userId) throws Exception {
        try {
            Post post = findPostById(postId);
            User user = userService.findUserById(userId);
            Set<Post> savedPosts=user.getSavedPosts();

            if(savedPosts==null){
                System.out.println("no saved posts");
                savedPosts=new HashSet<>();
                savedPosts.add(post);
                user.setSavedPosts(savedPosts);
            }
            else if(savedPosts.contains(post)) {
                savedPosts.remove(post);
                System.out.println("already saved post");
            }
            else {
                savedPosts.add(post);
                System.out.println("saved post");
            }
            System.out.println();
            userRepository.save(user);
            return post;
        }catch (Exception exception){
            throw new Exception(exception.toString());
        }
    }

    @Override
    public Post findPostById(Integer postId) throws Exception {
        Optional<Post> post= postRepository.findById(postId);
        if(post.isPresent())
            return post.get();
        throw new Exception("not post found with given id");
    }
}
