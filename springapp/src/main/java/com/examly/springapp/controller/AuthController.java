package com.examly.springapp.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import com.examly.springapp.config.JwtUtil;
import com.examly.springapp.config.UserPrinciple;
import com.examly.springapp.model.LoginDTO;
import com.examly.springapp.model.User;
// import com.examly.springapp.service.UserService;
import com.examly.springapp.service.UserServiceImpl;

import jakarta.annotation.security.PermitAll;

@CrossOrigin(origins = "https://8081-adebeedbfcacffaddfcbfefde.premiumproject.examly.io/")
@RestController
public class AuthController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private JwtUtil jwtUtil;


        @Autowired
    private UserDetailsService userDetailsService;

        @Autowired
    private AuthenticationManager authenticationManager;


    @PostMapping("/api/register")
    @CrossOrigin
    public ResponseEntity<?> registerUser(@RequestBody User user){
       if( userService.registerUser(user)!=null)
        return ResponseEntity.status(HttpStatus.CREATED).body(true);

        return ResponseEntity.status(400).body("user already exists");
    }


    @PostMapping("/api/login")
    @CrossOrigin
    public ResponseEntity<?> authenticateAndGetToken(@RequestBody LoginDTO user) throws Exception{
       try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
            );
        } catch (Exception e) { 
            
            return ResponseEntity.status(404).body("invalid");
        }

        final UserPrinciple userDetails = (UserPrinciple) userDetailsService.loadUserByUsername(user.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails.getUsername());

        Map<String, Object> response = new HashMap<>();
        response.put("token", jwt);
        response.put("username", userDetails.getUsername()); 
        response.put("role", userDetails.getAuthorities().toString()); 
        response.put("id",userDetails.getId());

        return ResponseEntity.ok(response);

    }
    
    @GetMapping("/api/test")
    @CrossOrigin(origins = "https://8081-adebeedbfcacffaddfcbfefde.premiumproject.examly.io" , allowedHeaders="*")
    public ResponseEntity<?> test()
    {   
        
        return ResponseEntity.ok("hello");
        
        


    }
}
