package com.example.web.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.web.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(metadata());
    }

    private ApiInfo metadata() {
        return new ApiInfoBuilder()
                .title("WebService")
                .description("Le service web RESTful permet d'exposer un message public de bienvenue et un message privé aux utilisateurs authentifiés sur le service.</br>" +
                        "Les échanges s'opèrent via des requêtes HTTP contenant des fichiers JSON dans le corps des requêtes (Body) et une clé 'Authorization' dans l'entête des requêtes pour les appels à des services sécurisés.")
                .version("0.0.1")
                .build();
    }
}
