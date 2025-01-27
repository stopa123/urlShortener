package com.mycompany.urlshortener.controllers;

import com.mycompany.urlshortener.services.AuthenticationService;
import com.mycompany.urlshortener.services.JwtService;
import com.mycompany.urlshortener.LoginResponse;
import com.mycompany.urlshortener.entities.Registration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {

    private final JwtService jwtService;

    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup") //completed and working
    public ResponseEntity<Registration> register(@RequestParam("email") String email, @RequestParam("firstname") String firstname, @RequestParam("lastname") String lastname, @RequestParam("phone") String phone, @RequestParam("organization") String organization, @RequestParam("password") String password) {

        Registration registerUser = new Registration(email, firstname, lastname, phone, organization, password);
        Registration registeredUser = authenticationService.signup(registerUser);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login") //completed and working
    public ResponseEntity<LoginResponse> authenticate(@RequestBody Registration loginUser) {

        Registration authenticatedUser = authenticationService.authenticate(loginUser);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse();

        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }

}
