package com.example;

import com.example.core.dao.UserDao;
import com.example.core.entities.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import java.util.Arrays;
import java.util.List;

/**
 * La classe {@code CoreApplication} représente la classe principale du service web RESTFul.
 * Cette classe est basée sur le framework Spring et contient la fonction principale {@link #main(String[])} appelée au
 * démarrage du framework.
 * @since 0.0.1
 * @author Duy Tran
 */
@SpringBootApplication
public class Application extends SpringBootServletInitializer implements CommandLineRunner {

	/** Dépot d'utilisateurs du service web. L'attribut {@link #userRepository} est utilisé ici dans le cadre de
	 * la version de démontration afin d'ajouter dès le démarrage un utilisateur par defaut.
	 * La base de donnée du projet est une base de donnée volatile H2 qui nécessite de remplir la base à chaque démarrage.
	 * <p> Note : il est important de supprimer cet attribut dans une version de production.</p>
	 */
	@Autowired
	private UserDao userRepository;

	/**
	 * Fonction principale exécutée à l'initialisation du framework Spring.
	 * @param args Tableau des arguments passées à l'appel de la fonction.  Par défaut, il ne contient rien.
	 */
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	// Surcharge ce methode de Spring pour pouvoir exécuter war file lors de déployement en production avec un servuer Tomcat
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(Application.class);
	}

	/**
	 * Fonction exécutée au démarrage du service. Il s'agit ici d'une surcharge afin de modifier
	 * le comportement original du démarrage afin d'ajouter un utilisateur par défaut pour la démonstration.
	 * <p> Note : il est important de supprimer cette fonction dans une version de production.</p>
	 * @param args Tableau des arguments passés à l'appel de la fonction. Par défaut, il ne contient rien.
	 * @throws Exception si l'utilisateur ne peut pas être crée ou s'il ne peut pas être ajouter à la collection des utilisateurs.
	 */
	@Override
	public void run(String... args) throws Exception {

		// Warning : Utiliser pour la démonstration pas en production
		List<AppUser> listOfUsersDemo = Arrays.asList(
				new AppUser("admin", "$2a$10$gDbTV0zgAmKX350ggJ7W7.zYUWR8H/KWzW9.yrl9z80uuzZ73kppy"),
				new AppUser("demoUser1", "$2a$10$gDbTV0zgAmKX350ggJ7W7.zYUWR8H/KWzW9.yrl9z80uuzZ73kppy"),
				new AppUser("demoUser2", "$2a$10$gDbTV0zgAmKX350ggJ7W7.zYUWR8H/KWzW9.yrl9z80uuzZ73kppy"),
				new AppUser("demoUser3", "$2a$10$gDbTV0zgAmKX350ggJ7W7.zYUWR8H/KWzW9.yrl9z80uuzZ73kppy"),
				new AppUser("demoUser4", "$2a$10$gDbTV0zgAmKX350ggJ7W7.zYUWR8H/KWzW9.yrl9z80uuzZ73kppy")
		);

		userRepository.saveAll(listOfUsersDemo);
/*
		userRepository.delete(userRepository.findByUserName("demoUser1"));

*/

	}

}
