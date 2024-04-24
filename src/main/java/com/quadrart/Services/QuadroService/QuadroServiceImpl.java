package com.quadrart.Services.QuadroService;

import java.util.List;
import java.util.Optional;

/*
 * Autowired para importar beans e construi-los sem necessidade de importa-los diretamente
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quadrart.Models.Quadro.Quadro;
import com.quadrart.Repositories.QuadroRepository;

@Service
public class QuadroServiceImpl implements QuadroService{

    @Autowired
    private QuadroRepository quadroRepository;
    

    @Override
    public List<Quadro> getAllQuadros() {
        return quadroRepository.findAll();
    }

    @Override
    public Optional<Quadro> getQuadro(Long id) {
        return quadroRepository.findById(id);
    }

    @Override
    public Quadro createQuadro(Quadro quadro) {
        return quadroRepository.save(quadro);
    }

    @Override
    public Quadro atualizarQuadro(Long id, Quadro quadro) {
        Optional<Quadro> existingQuadro = quadroRepository.findById(id);

        if (existingQuadro.isPresent()){
            quadro.setId(id);
            return quadroRepository.save(quadro);
        } else {
            throw new RuntimeException("Quadro não encontrado com id: " + id);
        }
    }

    @Override
    public String deleteQuadro(Long id) {
        quadroRepository.deleteById(id);
        return "Quadro com id: %s deletado com sucesso".formatted(id); 
    }
    
}
