package com.quadrart.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quadrart.Models.Quadro.Quadro;

<<<<<<< HEAD
/*
 * Interface repositório, que extende, JpaRepository. Essencialmente
 * permite uma integração dos models e MySQL. Permite o manuseio de uma
 * tabela criada dedicada ao objeto Modelo Quadro.
 */

=======
>>>>>>> a8e55b201a73f6e1e9db59dd7d139af492c00c78
@Repository
public interface QuadroRepository extends JpaRepository<Quadro, Long> {
    
}
