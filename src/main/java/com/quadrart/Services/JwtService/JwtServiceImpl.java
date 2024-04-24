package com.quadrart.Services.JwtService;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;


import com.quadrart.Models.Usuario.Usuario;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

/*
 * Lombok
 */

@Service
public class JwtServiceImpl implements JwtService {

    @Value("{${secret.signing.key}")
    private String secretKeyEncoded;

    @Override
    public String extractUsername(String jwt) {
        return extractClaim(jwt, Claims::getSubject);
    }

    @Override
    public String generateToken(Usuario usuario, long hours) {
       return generateToken(new HashMap(), usuario, hours);
    }

    @Override
    public boolean isTokenValid(String jwt, Usuario usuario) {
        final String username = extractUsername(jwt);
        return (username.equals(usuario.getUsername()) && !isTokenExpired(jwt));
    }


    private String generateToken(Map<String, Object> extraClaims, Usuario usuario, long hours){
        return Jwts
        .builder()
        .claims(extraClaims)
        .subject(usuario.getUsername())
        .expiration(new Date(System.currentTimeMillis() + hours * 1000 * 60))
        .issuedAt(new Date())
        .signWith(getSigninKey(secretKeyEncoded))
        .compact();
    }

    private Claims extractAllClaims(String jwt){
        return Jwts
                .parser()
                .verifyWith(getVerifyKey(secretKeyEncoded))
                .build()
                .parseSignedClaims(jwt)
                .getPayload();
    }

    private <T> T extractClaim(String jwt, Function<Claims, T> claimsResolvers) {
        final Claims claims = extractAllClaims(jwt);
        return claimsResolvers.apply(claims);
    }

    private Date extractExpiration(String jwt) {
        return extractClaim(jwt, Claims::getExpiration);
    }

    private boolean isTokenExpired(String jwt) {
        return extractExpiration(jwt).before(new Date());
    }

    private Key getSigninKey(String secretKeyEncoded) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKeyEncoded);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private SecretKey getVerifyKey(String secretKeyEncoded) {
        byte[] decodedKey = Base64.getDecoder().decode(secretKeyEncoded);
        return new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
    }
    
}
