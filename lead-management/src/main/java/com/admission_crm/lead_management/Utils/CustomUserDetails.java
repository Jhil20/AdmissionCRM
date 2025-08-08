package com.admission_crm.lead_management.Utils;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Data
public class CustomUserDetails {

    private String userId;
    private String username;
    private Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails(String userId, String username, List<? extends GrantedAuthority> authorities) {
        this.userId = userId;
        this.username = username;
        this.authorities = authorities;
    }
}
