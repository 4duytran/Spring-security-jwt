package com.example.core.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@ApiModel
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Commandes {

    /**
     * Identifiant unique d'une commande. Cette identifiant est géré automatiquement par la couche
     * Entity de Java.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COMMANDE_ID")
    private Long id;

    /**
     * La relation avec la table User
     * un User peut avoir 1 ou plusieurs commandes
     */
    @ManyToOne
    @JoinColumn(name="USER_Id")
    @JsonIgnore
    private AppUser user;

    /**
     * La relation avec la table fournisseur
     * un fournisseur peut fournir une ou plusieurs commandes
     */
    @ManyToOne
    @JoinColumn(name="Fournisseur_ID")
    private Fournisseur fournisseur;

    /**
     * La relation avec la table commande
     * Une commande peut avoir un ou plusieurs produits
     * Un produit peut ajouter dans une ou plusieurs commandes
     */
    @ManyToMany
    @JoinTable(
            name =  "COMMANDE_PRODUCT",
            joinColumns  = {@JoinColumn(name="COMMAND_ID")},
            inverseJoinColumns = {@JoinColumn(name="PRODUCT_ID" )}
    )
    private List<Products> products ;

    public Commandes(AppUser user, Fournisseur fournisseur) {
        this.user = user;
        this.fournisseur = fournisseur;
    }

    public Commandes(AppUser user, Fournisseur fournisseur, List<Products> products) {
        this.user = user;
        this.fournisseur = fournisseur;
        this.products = products;
    }

}
