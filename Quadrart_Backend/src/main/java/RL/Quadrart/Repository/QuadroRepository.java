package RL.Quadrart.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import RL.Quadrart.Model.Quadro;

@Repository
public interface QuadroRepository extends JpaRepository<Quadro, Long> {
    // You can add custom query methods if needed
    // For example:
    // List<Quadro> findByNomeArtista(String nomeArtista);
}