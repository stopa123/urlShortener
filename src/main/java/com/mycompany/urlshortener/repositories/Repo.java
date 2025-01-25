
package com.mycompany.urlshortener.repositories;

import com.mycompany.urlshortener.entities.urlShortenerMethods;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface Repo extends MongoRepository<urlShortenerMethods, Integer> {

    Optional<urlShortenerMethods> findUrlByshortenedURL(String shortenedURL);

    Optional<urlShortenerMethods> findIdByDescriptionAndEmail(String description, String email);

}
