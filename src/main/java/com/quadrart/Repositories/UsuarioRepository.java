package com.quadrart.Repositories;

import com.quadrart.Models.Usuario.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
<<<<<<< HEAD

/*
 * Interface repositório, que extende, JpaRepository. Essencialmente
 * permite uma integração dos models e MySQL. Permite o manuseio de uma
 * tabela criada dedicada ao objeto Modelo Usuario.
 */
=======
import java.util.List;

>>>>>>> a8e55b201a73f6e1e9db59dd7d139af492c00c78

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    Usuario findByUsername(String username);
    Usuario findByEmail(String email);
}
