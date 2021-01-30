package com.example.core.service;

import com.example.core.entities.AppUser;

import java.util.List;

/**
 * Représente un service pour gérer les utilisateurs du service web.
 * Cette interface a uniquement vocation de permettre l'utilisation d'injection des dépendances 
 * du framework Spring.
 * @since 0.0.1
 * @author Duy Tran
 */
public interface UserService {

    /**
     * Enregistre nouvel utilisateur du service web.
     * @param user Utilisateur du service web.
     * @return L'utilisateur du service web enregistré.
     */
    AppUser createUser(AppUser user);

    /**
     * Recherche un utilisateur par son nom d'utilisateur.
     * @param user Utilisateur du service web à rechercher.
     * @return L'utilisateur du service web trouvé.
     */
    AppUser findUserByUserName(AppUser user);


}
