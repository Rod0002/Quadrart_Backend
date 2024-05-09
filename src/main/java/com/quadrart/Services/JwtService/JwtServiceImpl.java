package com.quadrart.Services.JwtService;

<<<<<<< HEAD
=======
import java.security.Key;
import java.util.Base64;
>>>>>>> a8e55b201a73f6e1e9db59dd7d139af492c00c78
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;
<<<<<<< HEAD
=======
import javax.crypto.spec.SecretKeySpec;
>>>>>>> a8e55b201a73f6e1e9db59dd7d139af492c00c78

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

<<<<<<< HEAD
=======

>>>>>>> a8e55b201a73f6e1e9db59dd7d139af492c00c78
import com.quadrart.Models.Usuario.Usuario;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

/*
<<<<<<< HEAD
 * Código onde há a implementação da interface JwtService.
 * Aqui há a definição da lógica das funções declaradas 
 * na interface JwtService.
=======
 * Lombok
>>>>>>> a8e55b201a73f6e1e9db59dd7d139af492c00c78
 */

@Service
public class JwtServiceImpl implements JwtService {

<<<<<<< HEAD
    /*
     * Define um parametro chamado secretKeyEncoded.
     * A tag @Value pega um valor definido no arquivos properties
     * do projeto. A SecretKey é uma chave que será utilizada
     * para assinar o token JWT.
     */

    @Value("{${secret.signing.key}")
    private String secretKeyEncoded;

    /*
     * Extrai o subject do token JWT através
     * da função extractClaim.
     */

=======
    @Value("{${secret.signing.key}")
    private String secretKeyEncoded;

>>>>>>> a8e55b201a73f6e1e9db59dd7d139af492c00c78
    @Override
    public String extractUsername(String jwt) {
        return extractClaim(jwt, Claims::getSubject);
    }

<<<<<<< HEAD
    /*
     * Gera um token, pegando um objeto usuario, e o valor de horas
     * que define a expiração.
     */

    @Override
    public String generateToken(Usuario usuario, long hours) {
        return generateToken(new HashMap(), usuario, hours);
    }

    /*
     * Define se um token é válido, primeiramente extraindo
     * o username de um token jwt, e verifica se o token
     * é igual ao usuario recebido na função.
     */

=======
    @Override
    public String generateToken(Usuario usuario, long hours) {
       return generateToken(new HashMap(), usuario, hours);
    }

>>>>>>> a8e55b201a73f6e1e9db59dd7d139af492c00c78
    @Override
    public boolean isTokenValid(String jwt, Usuario usuario) {
        final String username = extractUsername(jwt);
        return (username.equals(usuario.getUsername()) && !isTokenExpired(jwt));
    }

<<<<<<< HEAD
    /*
     * Função que efetivamente gerá o token, recebendo um Map, contendo claims
     * adicionais
     * que podem ser adicionadas ao token, o usuário, e o número de horas.
     * 
     * Inicialmente um objeto Jwts é construido, e que recebe as claims, a data de
     * expiração
     * do token, e o momento de sua emissão. O token é então assinado com a chave
     * privada,
     * compactado e retornado.
     */
    private String generateToken(Map<String, Object> extraClaims, Usuario usuario, long hours) {
        return Jwts
                .builder()
                .claims(extraClaims)
                .subject(usuario.getUsername())
                .expiration(new Date(System.currentTimeMillis() + (hours * 1000 * 60 * 60)))
                .issuedAt(new Date())
                .signWith(getVerifyKey(secretKeyEncoded))
                .compact();
    }

    /*
     * Retorna todas as claims que podem estar contidas no Token Jwt.
     * 
     * A função recebe o token Jwt, e a chave privada é necessária para
     * para extrair a informação do token.
     */
    private Claims extractAllClaims(String jwt) {
=======

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
>>>>>>> a8e55b201a73f6e1e9db59dd7d139af492c00c78
        return Jwts
                .parser()
                .verifyWith(getVerifyKey(secretKeyEncoded))
                .build()
                .parseSignedClaims(jwt)
                .getPayload();
    }

<<<<<<< HEAD
    /*
     * Retorna apenas uma claim, porém, de qualquer forma, a função extractClaims
     * é chamada.
     */

    private <T> T extractClaim(String jwt, Function<Claims, T> claimsResolvers) {
        final Claims claims = extractAllClaims(jwt);
        System.out.println(claims);
        return claimsResolvers.apply(claims);
    }

    /*
     * Extrai a data de expiração do token.
     */

=======
    private <T> T extractClaim(String jwt, Function<Claims, T> claimsResolvers) {
        final Claims claims = extractAllClaims(jwt);
        return claimsResolvers.apply(claims);
    }

>>>>>>> a8e55b201a73f6e1e9db59dd7d139af492c00c78
    private Date extractExpiration(String jwt) {
        return extractClaim(jwt, Claims::getExpiration);
    }

<<<<<<< HEAD
    /*
     * Verifica se o token está expirado, e retorna true or false
     * baseado na resposta.
     */

    @Override
    public boolean isTokenExpired(String jwt) {
        return extractExpiration(jwt).before(new Date());
    }

    /*
     * Pega a string da secretKey, e faz o decode para
     * Base64.
     */

    private SecretKey getVerifyKey(String secretKeyEncoded) {
        byte[] decodedKey = Decoders.BASE64.decode(secretKeyEncoded);
        return Keys.hmacShaKeyFor(decodedKey);
    }

=======
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
    
>>>>>>> a8e55b201a73f6e1e9db59dd7d139af492c00c78
}
