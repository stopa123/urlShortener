
package com.mycompany.urlshortener.repositories;

import com.mycompany.urlshortener.entities.Registration;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RegRepo extends MongoRepository<Registration, Integer> {

    Optional<Registration> findFirstnameByEmail(String Email);

    Optional<Registration> findFirstnameByEmailAndPassword(String Email, String Password);

}


