package com.example.web.configuration;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Service utilitaire pour les jetons JWT.
 * Cette classe est basée sur le framework Spring avec le module Spring Security.
 * @since 0.0.1
 * @author Duy Tran
 */
@Service
public class JwtUtility {

    /** Clé privée de cryptage des jetons JWT. */
    @Value("${secret.key}")
    private String secret ;

    /**
     * Vérifie si le jeton JWT est expiré.
     * @param token Jeton JWT en chaîne de caractère.
     * @return Vrai si le jeton est expiré, Faux sinon.
     */
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * Génère un nouveau jeton JWT.
     * @param claims champs standard JWT.
     * @param subject Sujet (sub) du jeton JWT en chaîne de caractère.
     * @return Jeton JWT en chaîne de caractère.
     */
    private String createToken(Map<String, Object> claims, String subject) {
        Header header = Jwts.header();
        header.setType("JWT");
        return Jwts.builder().setClaims(claims).setSubject(subject).setHeader((Map<String, Object>)
                header).setIssuer("ABES").setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(addHoursToDate(new Date(System.currentTimeMillis()),1))
                .signWith(SignatureAlgorithm.HS256, secret).compact();
    }

    /**
     * Extrait les champs standard JWT (claims) d'un jeton JWT.
     * @param token Jeton JWT en chaîne de caractère.
     * @return Champs standard JWT (claims).
     */
    private Claims extractAllClaims(String token) {
        /**
         *
         * Actuellement cela provoque une erreur interne 500
         */
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    /**
     * Extrait le sujet (sub) d'un jeton JWT.
     * @param token Jeton JWT en chaîne de caractère.
     * @return Le sujet du jeton en chaîne de caractère.
     */
    public String extractSubject(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Extrait la date d'expiration (exp) d'un jeton JWT.
     * @param token Jeton JWT en chaîne de caractère.
     * @return Date d'expiration
     */
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     *
     * @param token
     * @param claimsResolver
     * @param <T>
     * @return
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Génère un jeton JWT à partir d'un sujet.
     * @param username Sujet (sub) du jeton JWT.
     * @return Jeton JWT en chaîne de caractère.
     */
    public String generateToken(String username) {
        Map<String, Object> claims;
        claims = new HashMap<>();
        return createToken(claims, username);
    }

    public boolean checkToken(String token, HttpServletRequest httpServletRequest) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (SignatureException ex) {
            httpServletRequest.setAttribute("token-invalid", "Token invalid");
        } catch (MalformedJwtException ex) {
            httpServletRequest.setAttribute("token-invalid", "Token invalid");
        } catch (ExpiredJwtException ex) {
            httpServletRequest.setAttribute("expired", "Token Exipred");
        } catch (UnsupportedJwtException ex) {
            httpServletRequest.setAttribute("token-invalid", "Token invalid");
        } catch (IllegalArgumentException ex) {
            httpServletRequest.setAttribute("token-invalid", "Token invalid");
        }

        return false;
    }

    /**
     * Vérifie la validité du jeton JWT avec :
     * <ul>
     *     <li>Le sujet du jeton doit correspondre au nom d'utilisateur.</li>
     *     <li>Le jeton ne doit pas être expiré.</li>
     * </ul>
     * @param token Jeton JWT en chaîne de caractère.
     * @param userDetails Utilisateur dans le framework Spring Security.
     * @return Vrai si le nom d'utilisateur et le sujet du jeton sont égaux et si le jeton n'est pas expiré, Faux sinon.
     */
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractSubject(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    /**
     * Ajoute un nombre d'heure à une date
     * @param date Date date initiale
     * @param hours int nombre d'heure à ajouter
     * @return Date date avec le nombre d'heure en plus
     */
    public Date addHoursToDate(Date date, int hours) {       
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, hours);
        return calendar.getTime();
    }

}
