package com.admissioncrm.authenticationservice.Utilities;

import com.admissioncrm.authenticationservice.Entities.CoreEntities.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Data
public class CustomUserPrinciple implements UserDetails {

    private String userId;
    private String username;
    private Collection<? extends GrantedAuthority> authorities;
    private User user;

    public CustomUserPrinciple(User user)
    {
        this.user = user;
    }

    public CustomUserPrinciple(String userId, String username,List<? extends GrantedAuthority> authorities) {
        this.userId = userId;
        this.username = username;
        this.authorities = authorities;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()));
    }


    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() { return user.getUsername(); }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
