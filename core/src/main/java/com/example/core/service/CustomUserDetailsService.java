package com.example.core.service;

import com.example.core.entities.AppUser;
import com.example.core.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Représente un service d'authentification des utilisateurs pour le framework Spring.
 * Cette classe est basée sur le module Spring Security.
 * @since 0.0.1
 * @author Duy Tran
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    /** Dépot d'utilisateurs du service web. */
    private final UserDao userRepository;

    /**
     * Construit un service d'authentification des utilisateurs pour le framework Spring 
     * à partir d'un dépot d'utilisateur du service web.
     * @param userRepository Dépot d'utilisateurs du service web.
     */
    @Autowired
    public CustomUserDetailsService(UserDao userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Récupère un utilisateur dans le dépot d'utilisateurs à partir de son nom d'utilisateur.
     * @param userName String Nom d'utilisateur à récupérer
     * @return Objet UserDetails de Spring Security
     * @throws UsernameNotFoundException si le nom d'utilisateur n'existe pas dans la collection.
     */
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        AppUser user = userRepository.findByUserName(userName);
        if(user == null) {
            throw new UsernameNotFoundException(userName);
        }
        return new User(user.getUserName(), user.getPassWord(), new ArrayList<>());
    }

}
