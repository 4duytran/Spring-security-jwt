package com.example.core.service;

import com.example.core.entities.AppUser;
import com.example.core.entities.Commandes;

import java.util.List;

public interface ICommandeService {

    Commandes createCommande(Commandes commande);
    Commandes findCommandeById(Commandes commande);
    List<Commandes> findCommandeByUser(AppUser user);

}
