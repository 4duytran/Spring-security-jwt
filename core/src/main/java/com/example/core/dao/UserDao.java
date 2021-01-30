package com.example.core.dao;

import com.example.core.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Représente un dépôt d'utilisateur du service web.
 * Cette classe est basée sur le framework Spring avec le module Spring Data JPA.
 * @since 0.0.1
 * @author Duy Tran
 */
@Repository
public interface UserDao extends JpaRepository<AppUser, String> {

    /**
     * Recherche un utilisateur du service web dans le dépot à partir de son nom d'utilisateur.
     * @param userName Nom d'utilisateur.
     * @return Un utilisateur du service web.
     */
    AppUser findByUserName(String userName);

}
