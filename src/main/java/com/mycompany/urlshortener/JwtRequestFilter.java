/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.urlshortener;

import org.springframework.web.filter.OncePerRequestFilter;

/**
 *
 * @author sikhu
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@Component
public abstract class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private jwtTokenMain jwtTokenMain;

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        final String authorizationHeader = request.getHeader("Authorization");

        String email = null;
        String jwt = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            email = jwtTokenMain.findMyEmail(jwt);
        }

        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            
            if (jwtTokenMain.validateToken(jwt, email)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(email, null, new ArrayList<>());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails((jakarta.servlet.http.HttpServletRequest) request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
            
        }
        chain.doFilter(request, response);
    }
}
