package com.quadrart.Handlers.CustomAuthenticationHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.quadrart.Models.Usuario.Usuario;
import com.quadrart.Services.UsuarioService.UsuarioService;

<<<<<<< HEAD
/*
 * Essa é uma classe de autenticação customizável. Ela executa a autenticação
 * e permite que o login possa ser executado com username ou e-mail.
 */
public class CustomAuthenticationProvider implements AuthenticationProvider {

    /*
     * Abaixo seguem os objetos que serão utilizados na lógica dos controladores,
     * como
     * por exemplo:
     * - UsuarioService (Objeto para manutenção de usuario na database);
     * - PasswordEncoder (Objeto para criptografação de senhas);
     */

=======
public class CustomAuthenticationProvider implements AuthenticationProvider {

>>>>>>> a8e55b201a73f6e1e9db59dd7d139af492c00c78
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PasswordEncoder passwordEncoder;

<<<<<<< HEAD
    /*
     * Essa é a função que executa a autenticação. Ela recebe um objeto Authentication
     * e extrae da Authnticação o parametro "Name" e "Credentials", que respectivamente
     * são, login e senha.
     * 
     * Após isso, é feito uma lógica para que o usuário não seja null, pois como o login
     * é email ou username, é necessário se realizar a busca com duas funções. loadUserbyUsername
     * que carrega um usuário por username ou loadUserByEmail que carrega um usuário por email.
     * 
     * Após isso, é verificado, se a senha obtida da base de dados é correspondente a mesma senha
     * enviada pelo usuário.
     * 
     * Após a verificação ser concluída um objeto Authentication é retornado, que garante que o usuário
     * está agora autenticado.
     */
=======
>>>>>>> a8e55b201a73f6e1e9db59dd7d139af492c00c78
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        final String login = authentication.getName();
        final String password = authentication.getCredentials().toString();

<<<<<<< HEAD
        Usuario usuario = usuarioService.loadUserByUsername(login);

        System.out.println(usuario);
        if (usuario == null){
            usuario = usuarioService.loadUserByEmail(login);
        } 
=======
        final Usuario usuario;

        if (usuarioService.loadUserByEmail(login) == null) {
            usuario = usuarioService.loadUserByUsername(login);
        } else {
            usuario = usuarioService.loadUserByEmail(login);
        }
>>>>>>> a8e55b201a73f6e1e9db59dd7d139af492c00c78

        if (passwordEncoder.matches(password, usuario.getPassword())) {
            throw new AuthenticationException("Credenciais inválidas"){};
        }

        Authentication authenticated = new UsernamePasswordAuthenticationToken(usuario.getUsername(), password,
                usuario.getAuthorities());

        return authenticated;
    }

<<<<<<< HEAD
    /*
     * Essa classe é implementadada do AuthenticationService, porém, não tem nenhuma utilidade agora.
     */
=======
>>>>>>> a8e55b201a73f6e1e9db59dd7d139af492c00c78
    @Override
    public boolean supports(Class<?> authentication) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'supports'");
    }

}
