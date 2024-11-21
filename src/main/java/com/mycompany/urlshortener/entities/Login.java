/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.urlshortener.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.security.NoSuchAlgorithmException;
import javax.crypto.KeyGenerator;

/**
 *
 * @author sikhu
 */
@Entity(name = "LOGIN")
public class Login {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    String token;
    String duration;
    String uid = Key();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
    
    public Login() {
    }

    //constructor
    public Login(String uid, String token, String duration) {
        super();
        this.uid = uid;
        this.token = token;
        this.duration = duration;
    }

    //Generate UID
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
