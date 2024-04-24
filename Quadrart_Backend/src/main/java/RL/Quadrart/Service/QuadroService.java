package RL.Quadrart.Service;

import RL.Quadrart.Model.Quadro;
import RL.Quadrart.Repository.QuadroRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuadroService {

    private final QuadroRepository quadroRepository;

    public QuadroService(QuadroRepository quadroRepository) {
        this.quadroRepository = quadroRepository;
    }

    public List<Quadro> getAllQuadros() {
        return quadroRepository.findAll();
    }

    public Optional<Quadro> getQuadroById(Long id) {
        return quadroRepository.findById(id);
    }

    public Quadro createQuadro(Quadro quadro) {
        // Perform any additional business logic if needed
        return quadroRepository.save(quadro);
    }

    public Quadro updateQuadro(Long id, Quadro updatedQuadro) {
        // Perform any additional business logic if needed
        Optional<Quadro> existingQuadro = quadroRepository.findById(id);
        if (existingQuadro.isPresent()) {
            updatedQuadro.setId(id);
            return quadroRepository.save(updatedQuadro);
        } else {
            // Handle the case where the Quadro with the given id is not found
            throw new RuntimeException("Quadro not found with id: " + id);
        }
    }

    public void deleteQuadro(Long id) {
        // Perform any additional business logic if needed
        quadroRepository.deleteById(id);
    }
}
