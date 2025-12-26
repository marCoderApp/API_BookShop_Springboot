package com.project.apibookshop.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtils {

    // Esta es la "llave maestra" para cifrar los tokens.
    // En producción, debería estar en un archivo de configuración seguro.
    private final String jwtSecret = "estaEsUnaClaveSuperSecretaYMuyLargaParaQueSeaSegura123456";
    private final int jwtExpirationMs = 86400000; // El token durará 24 horas

    private final Key key = Keys.hmacShaKeyFor(jwtSecret.getBytes());

    // 1. Método para generar el Token cuando el usuario hace login
    public String generateJwtToken(Authentication authentication) {
        UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject((userPrincipal.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // 2. Método para sacar el nombre de usuario de adentro de un Token
    public String getUserNameFromJwtToken(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token).getBody().getSubject();
    }

    // 3. Método para validar si el Token es real y no ha expirado
    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(authToken);
            return true;
        } catch (MalformedJwtException e) {
            System.err.println("Token inválido: " + e.getMessage());
        } catch (ExpiredJwtException e) {
            System.err.println("Token expirado: " + e.getMessage());
        } catch (UnsupportedJwtException e) {
            System.err.println("Token no soportado: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Token vacío: " + e.getMessage());
        }
        return false;
    }
}