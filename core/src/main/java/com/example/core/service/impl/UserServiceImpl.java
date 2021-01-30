package com.example.core.service.impl;

import com.example.core.entities.AppUser;
import com.example.core.exception.UserAlreadyExistsException;
import com.example.core.service.IUserService;
import com.example.core.dao.IUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Représente un service pour les utilisateurs du service web.
 * Cette classe est basée sur le framework Spring.
 * @since 0.0.1
 * @author Duy Tran
 */
@Service
public class UserServiceImpl implements IUserService {

    /** Dépot d'utilisateurs du service web. */
    private final IUserDao userRepository;

    /** Encodeur de mots de passe selon un algorithmede crpytage. */
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * Construit un service pour les utilisateurs du service web.
     * @param userRepository Dépot d'utilisateurs du service web
     * @param bCryptPasswordEncoder Encodeur de mots de passe selon un algorithmede crpytage.
     */
    @Autowired
    public UserServiceImpl(IUserDao userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    /**
     * Enregistre un nouvel utilisateur dans le dépot des utilisateurs du service web.
     * @param user Utilisateur du service web.
     * @return L'utilisateur du service web enregistré.
     */
    @Override
    public AppUser createUser(AppUser user) throws UserAlreadyExistsException {

        AppUser candidate = findUserByUserName(user);

        if (candidate != null) { 
            throw new UserAlreadyExistsException("Username already exists");
        }

        String passHash = bCryptPasswordEncoder.encode(user.getPassWord());
        user.setPassWord(passHash);
        return userRepository.save(user);
    }

    /**
     * Recherche un utilisateur par son nom d'utilisateur dans le dépot des utilisateurs du service web.
     * @param user Utilisateur du service web à rechercher.
     * @return L'utilisateur du service web trouvé.
     */
    @Override
    public AppUser findUserByUserName(AppUser user) {
        return userRepository.findByUserName(user.getUserName());
    }

    @Override
    public AppUser findUserByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public List<AppUser> getAllUsers() {
        return userRepository.findAll();
    }


}
