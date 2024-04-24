package com.quadrart.Controllers.QuadroController;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.quadrart.Services.QuadroService.QuadroService;
import com.quadrart.Services.StorageService.StorageService;

import jakarta.validation.Valid;

import com.quadrart.Models.Quadro.Quadro;
import com.quadrart.Models.Quadro.RequestQuadro;

import java.util.List;
import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("quadro")
public class QuadroController {

    @Autowired
    private QuadroService quadroService;

    @Autowired
    private StorageService storageService;

    @GetMapping
    public ResponseEntity<List<Quadro>> getAllQuadros(@RequestParam String param) {
        List<Quadro> quadros = quadroService.getAllQuadros();
        return ResponseEntity.ok(quadros);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getQuadro(@PathVariable Long id) {
        Optional<Quadro> optionalQuadro = quadroService.getQuadro(id);

        if (optionalQuadro.isPresent()) {
            Quadro quadro = optionalQuadro.get();

            return ResponseEntity.ok().body(quadro);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createQuadro(@Valid @RequestParam RequestQuadro requestQuadro, @RequestPart("file") MultipartFile file) {

        String fileType;
        if (file.getContentType().equals("image/jpg")) {
            fileType = ".jpg";
        } else if (file.getContentType().equals("image/jpeg")) {
            fileType = ".jpeg";     
        } else if (file.getContentType().equals("image/png")) {
            fileType = ".png";
        } else {
            return ResponseEntity.badRequest().body("File not supported");
        }

    	Quadro quadro = new Quadro(requestQuadro);  
        
        String uniqueIdentifier = String.valueOf(System.currentTimeMillis());

        String dataWithUniqueId = file.getOriginalFilename() + uniqueIdentifier;

        String imagemfinal = DigestUtils.sha256Hex(dataWithUniqueId) + fileType;

        
        quadro.setImagem(imagemfinal);
        
       
        storageService.store(file,imagemfinal);
        
        
        quadroService.createQuadro(quadro);

        return ResponseEntity.ok(quadro);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> updateQuadro(@PathVariable Long id, @Valid @RequestBody RequestQuadro requestQuadro) {
        Quadro quadro = quadroService.atualizarQuadro(id, new Quadro(requestQuadro));
        return ResponseEntity.ok(quadro);

    }

    @DeleteMapping(path = "/{id}")
    public String deleteQuadro(@PathVariable Long id) {
        return quadroService.deleteQuadro(id);
    }

    @GetMapping(path = "/image/{id}")
    public ResponseEntity<?> getQuadroImage(@PathVariable Long id) {
        Optional<Quadro> optionalQuadro = quadroService.getQuadro(id);

        if (optionalQuadro.isPresent()) {
            Quadro quadro = optionalQuadro.get();
            Resource file = storageService.loadAsResource(quadro.getImagem());
            if (file == null) {
                return ResponseEntity.notFound().build();
            }
            String filename = file.getFilename();
            String fileExtension = filename.substring(filename.lastIndexOf(".") + 1);
            String contentType = "image/" + fileExtension.toLowerCase();

            return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType)).body(file);
        } else {
            // Handle the case where Quadro is not found
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
