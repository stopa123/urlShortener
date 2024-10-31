/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.urlshortener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

/**
 *
 * @author sikhu
 */
@SpringBootApplication
@EntityScan("com.mycompany.urlshortener")
public class UrlShortener {

    public static void main(String[] args) {
      SpringApplication.run(UrlShortener.class, args);  
    }
}
