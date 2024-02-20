package com.example.SocialMediabackend.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JwtValidator extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException  {
//        request header like in postman
//        System.out.println(JwtConstant.JWT_HEADER);
        String jwtToken = request.getHeader(JwtConstant.JWT_HEADER);

        if (jwtToken!=null){
            try {
                String email = JwtProvider.getEmailFromJwtToken(jwtToken);
                List<GrantedAuthority> authorities = new ArrayList<>();

                Authentication authentication = new UsernamePasswordAuthenticationToken(email, null, authorities);

                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (Exception exception){
                throw new BadCredentialsException("error in generating token "+ exception.toString());
            }
        }
//        else {
//            throw new BadCredentialsException("provide valid token");
//        }
        filterChain.doFilter(request, response);
    }
}
