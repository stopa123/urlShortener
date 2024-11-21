/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.urlshortener.services;

import com.mycompany.urlshortener.entities.urlShortenerMethods;
import com.mycompany.urlshortener.repositories.Repo;
import java.util.Optional;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserAuthorizerService {

    private final Repo repo;

    public UserAuthorizerService(Repo repo) {
        this.repo = repo;
    }

    public urlShortenerMethods createUrl(urlShortenerMethods longURL) {

        urlShortenerMethods usm = new urlShortenerMethods();

        usm.setlongURL(longURL.getlongURL());
        usm.setshortenedURL(usm.getshortenedURL());
        usm.setEmail(getEmailAddress());
        usm.setDescription(longURL.getDescription());

        return repo.save(usm);
    }

    public String deleteUrl(urlShortenerMethods longURL) {

        Optional<urlShortenerMethods> data = repo.findIdByDescriptionAndEmail(longURL.getDescription(), getEmailAddress());

        int id = (data.isPresent()) ? data.get().getId() : 0;
        
        repo.deleteById(id);
        
        return "Record Deleted Successfully!";
    }

    //users of the authentication server
    public static String getEmailAddress() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {

            Object principal = authentication.getPrincipal();

            if (principal instanceof UserDetails userDetails) {
                return userDetails.getUsername();

            } else if (principal instanceof String string) {
                return string;
            }
        }
        return null;
    }

}
