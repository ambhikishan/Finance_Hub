// package com.examly.springapp.service;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.stereotype.Service;

// import com.examly.springapp.config.JwtUtils;
// import com.examly.springapp.exceptions.UserExistException;
// import com.examly.springapp.model.User;
// import com.examly.springapp.repository.UserRepo;

// @Service

// public class UserService {

//     @Autowired
//     private UserRepo userRepo;

//     @Autowired
//     private PasswordEncoder passwordEncoder;

//     @Autowired
//     private JwtUtils jwtService;

//     @Autowired
//     private AuthenticationManager authenticationManager;

//     public boolean registerUser(User user){
//         User existUser = userRepo.findByUsername(user.getUsername());
//         if(existUser != null){
//             throw new UserExistException();
//         }
//         user.setPassword(passwordEncoder.encode(user.getPassword()));
//         user = userRepo.save(user);
//         return true;
        
//     }

    
//     public String login(User user){
//         UsernamePasswordAuthenticationToken credentialtoken =  
//                 new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
//         Authentication authentication = authenticationManager.authenticate(credentialtoken);
//         if(authentication.isAuthenticated()){
//             return  jwtService.GenerateToken(user.getUsername());
//         } else {
//             throw new UsernameNotFoundException("invalid user request..!!");
//         }
//     }

    
// }
