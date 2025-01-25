/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.urlshortener.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import javax.crypto.KeyGenerator;
import org.springframework.data.mongodb.core.mapping.Document;

//@Entity(name = "URLSHORTENER")
@Document(collection = "users")
public class urlShortenerMethods {

    protected static HashMap<String, URL> shortURL = new HashMap<>(); //url hashmap
//@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    private String longURL;
    private String email;
    private String description;
    private String shortenedURL = "https://www.vinnoce.com/" + Key();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getlongURL() {
        return longURL;
    }

    public String getEmail() {
        return email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setlongURL(String longURL) {
        this.longURL = longURL;
    }

    public String getshortenedURL() {
        return shortenedURL;
    }

    public void setshortenedURL(String shortenedURL) {
        this.shortenedURL = shortenedURL;
    }

    //empty constructor
    public urlShortenerMethods() {
        super();
        this.longURL = getlongURL();
        this.shortenedURL = getshortenedURL();
    }

    //contructor to get url only
    public urlShortenerMethods(String longURL) {
        super();
        this.longURL = longURL;
    }

    //constructor
    public urlShortenerMethods(String longURL, String shortenedURL) {
        super();
        this.longURL = longURL;
        this.shortenedURL = shortenedURL;
    }

    //Generate Unique Number
    public static String Key() {
        KeyGenerator kg = null;

        try {
            kg = KeyGenerator.getInstance("AES");
            kg.init(256);

        } catch (NoSuchAlgorithmException e) {
            System.out.print(e);
        }

        String key = String.valueOf(kg.generateKey());
        String substring = key.substring(key.indexOf("@") + 1);

        return substring;
    }

}
