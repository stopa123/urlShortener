/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.urlshortener.controllers;

import com.mycompany.urlshortener.Authorization;
import com.mycompany.urlshortener.entities.urlShortenerMethods;
import com.mycompany.urlshortener.services.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author sikhu
 */
@RequestMapping("/auth")
@RestController
public class mainController {

    private final JwtService jwtService;
    private final Authorization authManager;

    public mainController(JwtService jwtService, Authorization authManager) {
        this.jwtService = jwtService;
        this.authManager = authManager;
    }

    @PostMapping("/create")
    public ResponseEntity<urlShortenerMethods> register(@RequestBody urlShortenerMethods usm) throws Exception {

        urlShortenerMethods auth = authManager.AuthenticateUserForURLReg(usm);

        return ResponseEntity.ok(auth);
    }

}
