/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.urlshortener;

import org.springframework.security.authorization.AuthenticatedAuthorizationManager;
import com.mycompany.urlshortener.entities.urlShortenerMethods;
import com.mycompany.urlshortener.repositories.RegRepo;
import com.mycompany.urlshortener.repositories.Repo;
import com.mycompany.urlshortener.services.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

/**
 *
 * @author sikhu
 */
@Service
public class Authorization {

    private final Repo repo;
    private final RegRepo regRepo;

    public Authorization(Repo repo, RegRepo regRepo) {
        this.repo = repo;
        this.regRepo = regRepo;
    }

    public urlShortenerMethods AuthenticateUserForURLReg(urlShortenerMethods usm) throws Exception {

        urlShortenerMethods us = new urlShortenerMethods();
        us.setlongURL(usm.getlongURL());

        repo.save(us);

        return null;
    }
}
