package com.mycompany.urlshortener;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.mycompany.urlshortener")
@EntityScan("com.mycompany.urlshortener")
public class UrlShortener {

    @Value("${spring.data.mongodb.uri}")
    private static String connectionString;

    public static void main(String[] args) {

        SpringApplication.run(UrlShortener.class, args);

    }

}
