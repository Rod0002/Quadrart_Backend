package RL.Quadrart.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import RL.Quadrart.Model.Usuario;
import RL.Quadrart.Repository.UsuarioRepository;


@Service
public class UserServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;


    public UserServiceImpl(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsername(email);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuário não encontrado: " + email);
        }
        return User.withUsername(usuario.getUsername())
                .password(usuario.getPassword())
                .roles("USER") // Pode adicionar mais roles se necessário
                .build();
    }

    @Override
    public void registerUser(Usuario usuario) {
        // Antes de salvar no banco de dados, criptografe a senha
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuarioRepository.save(usuario);
    }
}
