package com.quadrart.Services.JwtService;
import org.springframework.stereotype.Service;

import com.quadrart.Models.Usuario.Usuario;

<<<<<<< HEAD
/*
 * Interface de serviço JWT. Onde é definido
 * as funções que serão implementadas de forma obrigatória.
 */

@Service
public interface JwtService {
    String extractUsername(String jwt);
    
    String generateToken(Usuario usuario, long hours);
    
    boolean isTokenValid(String jwt, Usuario usuario);

    boolean isTokenExpired(String jwt);

=======
@Service
public interface JwtService {
    String extractUsername(String jwt);

    String generateToken(Usuario usuario, long hours);

    boolean isTokenValid(String jwt, Usuario usuario);
>>>>>>> a8e55b201a73f6e1e9db59dd7d139af492c00c78
}
