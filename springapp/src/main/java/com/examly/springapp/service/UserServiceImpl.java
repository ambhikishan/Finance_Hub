package com.examly.springapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.examly.springapp.config.JwtUtil;
import com.examly.springapp.model.LoginDTO;
import com.examly.springapp.model.User;
import com.examly.springapp.repository.*;
@Service
public class UserServiceImpl {
      @Autowired
    private PasswordEncoder passwordEncoder;

    

    @Autowired
    private JwtUtil jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    
    
    @Autowired
    UserRepo userRepo;
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);

    public User registerUser(User user)
    {   User u1 = userRepo.findByEmail(user.getEmail());
        User u2 = userRepo.findByUsername(user.getUsername());
        if(u1 == null && u2 == null)
        {      
                user.setPassword(encoder.encode(user.getPassword()));
            return userRepo.save(user);
        }
        return null;
    }

      //Bcrypt encoder give everty time different hash for the same raw text
    public String loginUser(LoginDTO user)
    {
        //  UsernamePasswordAuthenticationToken credentialtoken =  
        //         new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        // Authentication authentication = authenticationManager.authenticate(credentialtoken);
        // if(authentication.isAuthenticated()){
        //     return  jwtService.generateToken(user.getUsername());
        // } else {
        //     throw new UsernameNotFoundException("invalid user request..!!");
        // }
        
        return null;
    }

    public int usedId(String username)
    {
        return userRepo.findByUsername(username).getUserId();
    }
    }
