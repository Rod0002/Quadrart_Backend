package com.quadrart.Services.QuadroService;

/**
 * Tipos primitivos/classes
 */
import java.util.List;
import java.util.Optional;

import com.quadrart.Models.Quadro.Quadro;


public interface QuadroService {
    List<Quadro> getAllQuadros();

    Optional<Quadro> getQuadro(Long id);

    Quadro createQuadro(Quadro quadro);

    Quadro atualizarQuadro(Long id, Quadro quadro);

    String deleteQuadro(Long id);

}
