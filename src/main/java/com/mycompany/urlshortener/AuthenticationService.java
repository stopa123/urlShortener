/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.urlshortener;

/**
 *
 * @author sikhu
 */
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final RegRepo regRepo;
    private final Repo repo;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(RegRepo regRepo, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, Repo repo) {
        this.authenticationManager = authenticationManager;
        this.regRepo = regRepo;
        this.repo = repo;
        this.passwordEncoder = passwordEncoder;
    }

    public Registration signup(Registration input) {
        Registration user = new Registration();

        user.setEmail(input.getEmail());
        user.setFirstname(input.getFirstname());
        user.setLastname(input.getLastname());
        user.setOrganization(input.getOrganization());
        user.setPhone(input.getPhone());
        user.setPassword(passwordEncoder.encode(input.getPassword()));

        return regRepo.save(user);
    }

    public Registration authenticate(Registration input) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(input.getEmail(), input.getPassword()));

        return regRepo.findFirstnameByEmail(input.getEmail()).orElseThrow();
    }

    public urlShortenerMethods createURL(urlShortenerMethods us, Registration reg) {

        urlShortenerMethods usm = new urlShortenerMethods(); //call shortener methods class
        urlShortenerMethods plz = new urlShortenerMethods(); //generate short URL using empty Constructor.

        usm.setlongURL(us.getlongURL());
        usm.setshortenedURL(plz.getshortenedURL());
        
        //set verification condition here.
        

        urlShortenerMethods longURL = new urlShortenerMethods(us.getlongURL(), plz.getshortenedURL()); //constructor to save into Database 
        return repo.save(longURL);

    }
}
