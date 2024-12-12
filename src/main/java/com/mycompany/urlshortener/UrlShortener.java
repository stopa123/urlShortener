
package com.mycompany.urlshortener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.mycompany.urlshortener")
public class UrlShortener {

    public static void main(String[] args) {
        
      SpringApplication.run(UrlShortener.class, args);  
    }
}
