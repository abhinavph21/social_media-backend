package com.example.SocialMediabackend.controller;

import com.example.SocialMediabackend.config.JwtProvider;
import com.example.SocialMediabackend.model.User;
import com.example.SocialMediabackend.repository.UserRepository;
import com.example.SocialMediabackend.request.LoginRequest;
import com.example.SocialMediabackend.service.AuthResponse;
import com.example.SocialMediabackend.service.CustomerUserDetailsService;
import com.example.SocialMediabackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    private CustomerUserDetailsService customerUserDetailsService;
    @PostMapping("/signup")
    public AuthResponse createUser(@RequestBody User inputUser) throws Exception {
        System.out.println("this endpoint");
        Optional<User> existingUser = userRepository.findByEmail(inputUser.getEmail());
        if(existingUser.isPresent()){
            throw new Exception("email already in use");
        }
        User newUser = new User();
        newUser.setUsername(inputUser.getUsername());
        newUser.setFirstName(inputUser.getFirstName());
        newUser.setLastName(inputUser.getLastName());
        newUser.setEmail(inputUser.getEmail());
        newUser.setGender(inputUser.getGender());
        System.out.println(newUser+ " "+inputUser);
        newUser.setPassword(passwordEncoder.encode(inputUser.getPassword()));
        System.out.println(newUser);
        try {
            User savedUser= userRepository.saveAndFlush(newUser);
            System.out.println(savedUser + " saved user");
            Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser.getEmail(), savedUser.getPassword());
//            file
            String token = JwtProvider.generateToken(authentication);

            AuthResponse res = new AuthResponse(token, "registered user successfully");
            return res;
        } catch (Exception exception){
            throw new Exception(exception.toString());
        }
    }
    @PostMapping("/signin")
    public AuthResponse signin(@RequestBody LoginRequest loginRequest){
        Authentication authentication = authenticate(loginRequest.getEmail(), loginRequest.getPassword());
        String token = JwtProvider.generateToken(authentication);
        AuthResponse res=  new AuthResponse(token, "signin success");
        return  res;
    }
    private Authentication authenticate(String email, String password){
        UserDetails userDetails = customerUserDetailsService.loadUserByUsername(email);
        if (userDetails==null)
            throw new BadCredentialsException("invalid username");
        if(!passwordEncoder.matches(password, userDetails.getPassword()))
            throw new BadCredentialsException("password doesn't match");
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}
