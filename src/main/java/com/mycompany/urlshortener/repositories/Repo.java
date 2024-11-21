/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.urlshortener.repositories;

import com.mycompany.urlshortener.entities.urlShortenerMethods;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author sikhu
 */
@Repository
public interface Repo extends JpaRepository<urlShortenerMethods, Integer> {

    Optional<urlShortenerMethods> findUrlByshortenedURL(String shortenedURL);

    Optional<urlShortenerMethods> findIdByDescriptionAndEmail(String description, String email);

}
