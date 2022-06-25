package com.challengealkemy.challengealkemy.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.challengealkemy.challengealkemy.exceptions.AppException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

@Component
public class JwtTokenProvider {
    @Value("${app.jwt-secret}")
    private String jwtSecret;

    @Value("${app.jwt-expiration-milliseconds}")
    private int jwtExpirationMs;

    public String generarToken(Authentication authentication){
        String username = authentication.getName();
        Date fechaActual = new Date();
        Date fechaExpiracion = new Date(fechaActual.getTime() + jwtExpirationMs);

        String token = Jwts.builder().setSubject(username).setIssuedAt(new Date()).setExpiration(fechaExpiracion).signWith(SignatureAlgorithm.HS512, jwtSecret).compact();

        return token;
    }

    public String obtenerUserDelJwt(String token){
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();

        return claims.getSubject();
    }

    public boolean validarToken(String token){
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (SignatureException e) {
            throw new AppException(HttpStatus.BAD_REQUEST, "Firma JWT no válida");
        }
        catch(MalformedJwtException e){
            throw new AppException(HttpStatus.BAD_REQUEST, "Token JWT no válido");
        }
        catch(ExpiredJwtException e){
            throw new AppException(HttpStatus.BAD_REQUEST, "Token JWT caducado");
        }
        catch(IllegalArgumentException e){
            throw new AppException(HttpStatus.BAD_REQUEST, "La cadena claims JWT esta vacia");
        }
    }
}
