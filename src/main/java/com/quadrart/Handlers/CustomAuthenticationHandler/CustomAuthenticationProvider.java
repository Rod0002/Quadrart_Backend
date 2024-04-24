package com.quadrart.Handlers.CustomAuthenticationHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.quadrart.Models.Usuario.Usuario;
import com.quadrart.Services.UsuarioService.UsuarioService;

public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        final String login = authentication.getName();
        final String password = authentication.getCredentials().toString();

        final Usuario usuario;

        if (usuarioService.loadUserByEmail(login) == null) {
            usuario = usuarioService.loadUserByUsername(login);
        } else {
            usuario = usuarioService.loadUserByEmail(login);
        }

        if (passwordEncoder.matches(password, usuario.getPassword())) {
            throw new AuthenticationException("Credenciais inválidas"){};
        }

        Authentication authenticated = new UsernamePasswordAuthenticationToken(usuario.getUsername(), password,
                usuario.getAuthorities());

        return authenticated;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'supports'");
    }

}
