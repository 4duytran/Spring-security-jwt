package com.example.web.controller;

import com.example.core.entities.AppUser;
import com.example.core.entities.Commandes;
import com.example.core.service.ICommandeService;
import com.example.core.service.IUserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Controlleur d'API RESTful pour toutes les routes privées.
 * Cette classe est basée sur le framework Spring avec le module Spring Web.
 * @since 0.0.1
 * @author Duy Tran
 */
@CrossOrigin(origins = "https://hello-dev.abes.fr")
@RestController
@RequestMapping("/api/secured")
public class SecuredController {

    private final IUserService userService;

    private final ICommandeService commandeService;


    @Autowired
    public SecuredController(IUserService userService, ICommandeService commandeService) {
        this.userService = userService;

        this.commandeService = commandeService;
    }

    /**
     * Traitement d'une requête GET sur la route '/secured'.
     * @return Une chaîne de caractère.
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(
            value = "Message privé",
            notes = "Retourne un message privé")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Opéaration terminée avec succès."),
            @ApiResponse(code = 503, message = "Service indisponible."),
            @ApiResponse(code = 400, message = "Mauvaise requête. Le paramètre problématique sera précisé par le message d'erreur. Par exemple : paramètre manquant, adresse erronnée..."),
            @ApiResponse(code = 404, message = "Opération a échoué."),
    })
    public Map displaySecureHome() {

        return Collections.singletonMap("response", "Hello from ABES - {* PRIVATE *} API PAGE");
    }

    /**
     * Traitement d'une requête GET sur la route '/secured'.
     * @return Une chaîne de caractère.
     */
    @GetMapping("/commande")
    @ApiOperation(
            value = "Liste de commandes",
            notes = "Retourne une liste de tous les commandes avec les produits et le fournisseur")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Opéaration terminée avec succès."),
            @ApiResponse(code = 503, message = "Service indisponible."),
            @ApiResponse(code = 400, message = "Mauvaise requête. Le paramètre problématique sera précisé par le message d'erreur. Par exemple : paramètre manquant, adresse erronnée..."),
            @ApiResponse(code = 404, message = "Opération a échoué."),
    })
    public List<Commandes> displaySecureCommandes(Authentication authentication) {

        AppUser user = userService.findUserByUserName(authentication.getName());
        return commandeService.findCommandeByUser(user);
    }
}
