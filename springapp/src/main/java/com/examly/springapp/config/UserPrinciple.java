package com.examly.springapp.config;

import java.util.ArrayList;
import java.util.Collection;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.*;
import com.examly.springapp.model.User;


public class UserPrinciple implements UserDetails{
    private String username;
    private String password;
    private int id;
    Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

    
    public UserPrinciple(User user) {
        this.username = user.getUsername();
        this.password= user.getPassword();
        this.id = user.getUserId();
        authorities.add(new SimpleGrantedAuthority(user.getUserRole()));

    }
    
    public int getId()
    {
        return this.id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    
}
