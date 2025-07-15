package com.admissioncrm.authenticationservice.DTO.Jwt;

import com.admissioncrm.authenticationservice.Entities.CoreEntities.Role;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
public class JwtResponse {

    private String jwtToken;
    private Role role;

    public JwtResponse(String jwtToken, Role role) {
        this.jwtToken = jwtToken;
        this.role = role;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
