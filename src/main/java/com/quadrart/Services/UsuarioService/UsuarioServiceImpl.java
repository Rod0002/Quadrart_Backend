package com.quadrart.Services.UsuarioService;

<<<<<<< HEAD
import java.util.Optional;

=======
>>>>>>> a8e55b201a73f6e1e9db59dd7d139af492c00c78
import org.springframework.stereotype.Service;

import com.quadrart.Models.Usuario.Usuario;
import com.quadrart.Repositories.UsuarioRepository;

import lombok.RequiredArgsConstructor;

<<<<<<< HEAD
/*
 * Serviço responsável por criar um toolset para 
 * buscar, criar, editar e deletar usuários.
 */
=======
>>>>>>> a8e55b201a73f6e1e9db59dd7d139af492c00c78
@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {
    
<<<<<<< HEAD
    /*
     * UsuarioRepository para se ter acesso a funções
     * que interagem com a base de dados.
     * 
     */
    private final UsuarioRepository usuarioRepository;

    /*
     * Recebe um username e carrega um usuário com
     * esse username;
     */
=======
    private final UsuarioRepository usuarioRepository;

>>>>>>> a8e55b201a73f6e1e9db59dd7d139af492c00c78
    @Override
    public Usuario loadUserByUsername(String username) {
            return usuarioRepository.findByUsername(username);
        
    }

<<<<<<< HEAD
    /*
     * Recebe um e-mail, e carrega um usuário com esse
     * e-mail
     */
=======
>>>>>>> a8e55b201a73f6e1e9db59dd7d139af492c00c78
    @Override
    public Usuario loadUserByEmail(String email){
        return usuarioRepository.findByEmail(email);
    }

<<<<<<< HEAD
    /*
     * Recebe um usuário, e cria um usuário.
     */
=======
>>>>>>> a8e55b201a73f6e1e9db59dd7d139af492c00c78
    @Override
    public Usuario createUser(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

<<<<<<< HEAD
    /*
     * Recebe um id, e deleta um usuário de dado ID.
     */
=======
>>>>>>> a8e55b201a73f6e1e9db59dd7d139af492c00c78
    @Override
    public String deleteUser(Long id){
        usuarioRepository.deleteById(id);
        return "O usuário de ID: %s foi deletado".formatted(id);
    }
<<<<<<< HEAD

    @Override
    public Usuario updateUser(Usuario usuario) {
        Usuario user = usuarioRepository.findByUsername(usuario.getUsername());
        if (user != null){
            return usuarioRepository.save(user);
        } else {
            return user;
        }
    }
=======
>>>>>>> a8e55b201a73f6e1e9db59dd7d139af492c00c78
    
}
