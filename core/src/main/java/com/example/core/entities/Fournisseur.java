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
public class Fournisseur {

    /**
     * Identifiant unique d'un fournisseur. Cette identifiant est géré automatiquement par la couche
     * Entity de Java.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Fournisseur_ID")
    private Long id;

    @Column(name = "Fournisseur_NAME")
    private String name;

    /**
     * La relation avec la table Commandes
     */
    @OneToMany(mappedBy = "fournisseur",
            cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Commandes> commandes;

    public Fournisseur(String name, List<Commandes> commandes) {
        this.name = name;
        this.commandes = commandes;
    }

    public Fournisseur(String name) {
        this.name = name;
    }
}
