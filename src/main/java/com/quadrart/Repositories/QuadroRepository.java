package com.quadrart.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quadrart.Models.Quadro.Quadro;

@Repository
public interface QuadroRepository extends JpaRepository<Quadro, Long> {
    
}
