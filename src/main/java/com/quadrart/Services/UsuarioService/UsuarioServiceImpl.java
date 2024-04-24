package com.quadrart.Services.UsuarioService;

import org.springframework.stereotype.Service;

import com.quadrart.Models.Usuario.Usuario;
import com.quadrart.Repositories.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {
    
    private final UsuarioRepository usuarioRepository;

    @Override
    public Usuario loadUserByUsername(String username) {
            return usuarioRepository.findByUsername(username);
        
    }

    @Override
    public Usuario loadUserByEmail(String email){
        return usuarioRepository.findByEmail(email);
    }

    @Override
    public Usuario createUser(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public String deleteUser(Long id){
        usuarioRepository.deleteById(id);
        return "O usuário de ID: %s foi deletado".formatted(id);
    }
    
}
