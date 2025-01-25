
package com.mycompany.urlshortener.repositories;

import com.mycompany.urlshortener.entities.Login;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface LoginRepo extends MongoRepository<Login, Long> {

    Optional<Login> findByToken(String token);

}
