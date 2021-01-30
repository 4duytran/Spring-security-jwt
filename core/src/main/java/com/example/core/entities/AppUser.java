package com.example.core.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Représente un utilisateur du service web. {@code AppUser} est synchronisé avec une base de données via Entity.
 * La framework lombok permet de générer des méthodes courantes comme Getter/Setter directement à la compilation.
 * @since 0.0.1
 * @author Duy Tran
 */
@Entity
@ApiModel
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Table(name= "UTILISATEUR")
public class AppUser {

    /**
     * Identifiant unique d'un utilisateur. Cette identifiant est géré automatiquement par la couche
     * Entity de Java.
     */
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    @ApiModelProperty(value = "identifiant de l'utilisateur", name = "identityNumber", dataType = "Integer", example = "1")
    private Integer identityNumber;

    /** Nom d'utilisateur  */
    @Column(name = "USER_NAME")
    @NotNull(message = "Le nom d'utilisateur ne doit pas être null")
    @NotEmpty(message = "Le nom d'utilisateur ne doit pas être vide")
    @ApiModelProperty(value = "nom de l'utilisateur", name = "userName", dataType = "String", example = "corentin")
    private String userName;

    /** Mot de passe */
    @Column(name = "USER_PASSWORD")
    @NotNull(message = "Le mot de passe ne doit pas être null")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", message = "Le mot de passe ne respecte pas les règles de sécurité")
    @ApiModelProperty(value = "mot de passe de l'utilisateur", name = "passWord", dataType = "String", example = "motDePasseC0!mplex")
    private String passWord;


    /**
     * Construit un utilisateur à partir de son nom d'utilisateur et de son mot de passe.
     * @param userName Chaîne de caractère du nom de l'utilisateur.
     * @param password Chaîne de caractère du mot de passe de l'utilisateur.
     */
    public AppUser(String userName, String password) {
        this.userName = userName;
        this.passWord = password;
    }
}
