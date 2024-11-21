/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.urlshortener.repositories;

import com.mycompany.urlshortener.entities.Login;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author sikhu
 */
public interface LoginRepo extends JpaRepository<Login, Long> {

    Optional<Login> findByToken(String token);

}
