package RL.Quadrart.Service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import RL.Quadrart.Model.Usuario;

public interface UsuarioService extends UserDetailsService {
	UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

	void registerUser(Usuario user);

}
