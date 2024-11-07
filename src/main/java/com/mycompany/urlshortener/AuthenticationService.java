/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.urlshortener;

/**
 *
 * @author sikhu
 */
import java.util.Optional;
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

    public String createURL(Registration reg, urlShortenerMethods us) {

        String email = reg.getEmail();
        String longUrl = us.getlongURL(); // Assume you want longURL here 
        
        if (longUrl == null) {
            return "No long URL provided";
        }
        
        System.out.println(longUrl);
        
        urlShortenerMethods usm = new urlShortenerMethods();
        usm.setlongURL(longUrl);
        
        // Set the long URL 
        String generatedShortURL = usm.getshortenedURL();
        
        // Generate and retrieve the shortened URL 
        usm.setshortenedURL(generatedShortURL);
        
        // Set the shortened URL // Verify email 
        if (regRepo.findFirstnameByEmail(email).isPresent()) {
            repo.save(usm);
        } else {
            return email + " Not registered with us";
        }
        return generatedShortURL;

    }

}
