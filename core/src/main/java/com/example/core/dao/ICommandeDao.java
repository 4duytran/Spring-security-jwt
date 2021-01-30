package com.example.core.dao;

import com.example.core.entities.AppUser;
import com.example.core.entities.Commandes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Représente un dépôt de commande du service web.
 * Cette classe est basée sur le framework Spring avec le module Spring Data JPA.
 * @since 0.0.1
 * @author Duy Tran
 */
@Repository
public interface ICommandeDao extends JpaRepository<Commandes, Long> {

    List<Commandes> findAllByUser(AppUser user);

}
