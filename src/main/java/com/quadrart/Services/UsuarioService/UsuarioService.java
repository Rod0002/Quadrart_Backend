package com.quadrart.Services.UsuarioService;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.quadrart.Models.Usuario.Usuario;

<<<<<<< HEAD
/*
 * Interface de serviço Usuario. Onde é definido
 * as funções que serão implementadas de forma obrigatória.
 */
=======
>>>>>>> a8e55b201a73f6e1e9db59dd7d139af492c00c78
public interface UsuarioService extends UserDetailsService{
    Usuario loadUserByUsername(String username);

    Usuario loadUserByEmail(String email);

    Usuario createUser(Usuario usuario);

    String deleteUser(Long Id);
<<<<<<< HEAD

    Usuario updateUser(Usuario usuario);
=======
>>>>>>> a8e55b201a73f6e1e9db59dd7d139af492c00c78
}
