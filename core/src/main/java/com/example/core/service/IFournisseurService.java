package com.example.core.service;

import com.example.core.entities.Fournisseur;

public interface IFournisseurService {

    Fournisseur createFournisseur(Fournisseur fournisseur);
    Fournisseur FindFournissuerById(Fournisseur fournisseur);

}
