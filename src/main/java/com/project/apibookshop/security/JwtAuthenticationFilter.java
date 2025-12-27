package com.project.apibookshop.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserDetailsService userDetailsService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
                throws ServletException, IOException {
        try{
            //EXTRACT TOKEN FROM THE HEADER "AUTHORIZATION"
            String jwt = parseJwt(request);


            if(jwtUtils.validateJwtToken(jwt) && jwt != null){
                //GET USERNAME FROM TOKEN
                String username = jwtUtils.getUserNameFromJwtToken(jwt);

                //SEARCH USER IN THE DATABASE BY USERNAME
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                //CREATE OFFICIAL SPRING SECURITY AUTHENTICATION OBJECT
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities()
                );

                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                //SAVE USER IN THE CONTEXT
                SecurityContextHolder.getContext().setAuthentication(authentication);

            }

        } catch(Exception e){
            logger.error("Could not establish connection with user: {}", e);
        }

        filterChain.doFilter(request, response);
    }

    private String parseJwt(HttpServletRequest request){
        String bearerToken = request.getHeader("Authorization");
        if(bearerToken != null && bearerToken.startsWith("Bearer ")){
            return bearerToken.substring(7);
        }
        return null;
    }


}
