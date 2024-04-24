package com.quadrart.Services.JwtService;
import org.springframework.stereotype.Service;

import com.quadrart.Models.Usuario.Usuario;

@Service
public interface JwtService {
    String extractUsername(String jwt);

    String generateToken(Usuario usuario, long hours);

    boolean isTokenValid(String jwt, Usuario usuario);
}
