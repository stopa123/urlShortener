/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.urlshortener.controllers;

import com.mycompany.urlshortener.entities.urlShortenerMethods;
import com.mycompany.urlshortener.services.UserAuthorizerService;
import static com.mycompany.urlshortener.services.UserAuthorizerService.getEmailAddress;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
public class AuthorizationController {

    private final UserAuthorizerService userAuth;

    //constructor
    public AuthorizationController(UserAuthorizerService userAuth) {
        this.userAuth = userAuth;
    }

    @GetMapping("/useremail")
    public ResponseEntity<String> register() throws Exception {

        return ResponseEntity.ok(getEmailAddress());
    }

    @PostMapping("/createurl")
    public ResponseEntity<urlShortenerMethods> createURL(@RequestBody urlShortenerMethods url) {

        urlShortenerMethods longURL = (getEmailAddress() != null) ? userAuth.createUrl(url) : null;

        return ResponseEntity.ok(longURL);
    }

    @DeleteMapping("/deleteurl")
    public ResponseEntity<Object> DeleteURL(@RequestBody urlShortenerMethods url) {

        var deleteURL = (getEmailAddress() != null) ? userAuth.deleteUrl(url) : "Error";
        
        return ResponseEntity.ok(deleteURL); 
        
    }

}
