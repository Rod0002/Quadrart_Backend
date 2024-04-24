package RL.Quadrart.Controller;

import RL.Quadrart.Model.Quadro;
import RL.Quadrart.Service.QuadroService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;


import java.util.List;

@RestController
@RequestMapping("/quadro")
public class QuadroController {

    private final QuadroService quadroService;

    public QuadroController(QuadroService quadroService) {
        this.quadroService = quadroService;
    }

    // Get all Quadros
    @GetMapping
    public ResponseEntity<List<Quadro>> getAllQuadros() {
        List<Quadro> quadros = quadroService.getAllQuadros();
        return new ResponseEntity<>(quadros, HttpStatus.OK);
    }

    // Get a specific Quadro by ID
    @GetMapping("/{id}")
    public ResponseEntity<Quadro> getQuadroById(@PathVariable Long id) {
        Optional<Quadro> optionalQuadro = quadroService.getQuadroById(id);

        if (optionalQuadro.isPresent()) {
            Quadro quadro = optionalQuadro.get();
            return new ResponseEntity<>(quadro, HttpStatus.OK);
        } else {
            // Handle the case where Quadro is not found
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Create a new Quadro
    @PostMapping
    public ResponseEntity<Quadro> createQuadro(@RequestBody Quadro quadro) {
        Quadro createdQuadro = quadroService.createQuadro(quadro);
        return new ResponseEntity<>(createdQuadro, HttpStatus.CREATED);
    }

    // Update an existing Quadro
    @PutMapping("/{id}")
    public ResponseEntity<Quadro> updateQuadro(@PathVariable Long id, @RequestBody Quadro updatedQuadro) {
        Quadro quadro = quadroService.updateQuadro(id, updatedQuadro);
        return new ResponseEntity<>(quadro, HttpStatus.OK);
    }

    // Delete a Quadro by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuadro(@PathVariable Long id) {
        quadroService.deleteQuadro(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}