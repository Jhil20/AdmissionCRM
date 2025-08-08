package com.admission_crm.lead_management.Config;

import com.admission_crm.lead_management.Utils.CustomUserDetails;
import com.admission_crm.lead_management.Utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    JwtUtils jwtUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String token = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);

            try {
                if (jwtUtils.isTokenValid(token)) {
                    Claims claims = jwtUtils.extractAllClaims(token);
                    String username = claims.getSubject();
                    String userId = claims.get("userId", String.class);

                    // Assuming roles are stored as a List<String> claim called "roles"
                    String role = claims.get("role", String.class);


                    List<SimpleGrantedAuthority> authorities=new ArrayList<>() ;
                    authorities.add(new SimpleGrantedAuthority("ROLE_"+role));

                    CustomUserDetails principle = new CustomUserDetails(username,userId,authorities);
                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(principle, null,principle.getAuthorities() );
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            } catch (Exception e) {
                logger.warn("JWT validation failed: {}");
            }
        }
        filterChain.doFilter(request, response);
    }
}
