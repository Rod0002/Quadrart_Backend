package com.quadrart.Models.Quadro;

<<<<<<< HEAD
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

/*
 * Modelo para receber uma requisição de criação de quadro e atualização de quadro.
 * - Contém tags de verifição para sem utilizados pelo @Valid.
 * Exemplos:
 * - @NotBlank
 * - @Size 
 */
=======
import java.time.Year;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

>>>>>>> a8e55b201a73f6e1e9db59dd7d139af492c00c78
public record RequestQuadro(
        @NotBlank
        @Size(min=1, max=32)
        String nomeArtista,

        @NotBlank
        @Size(min=1, max=40)
        String nomeAlbum,

<<<<<<< HEAD
        long ano,
=======
        
        @NotBlank
        @PastOrPresent
        Year ano,
>>>>>>> a8e55b201a73f6e1e9db59dd7d139af492c00c78

        @NotBlank
        @Size(min=3, max=20)
        String genero,

<<<<<<< HEAD
        @Max(999)
        Long duracao,

        MultipartFile file
=======
        @NotBlank
        @Max(999)
        Long duracao,

        @NotBlank
        String imagem
>>>>>>> a8e55b201a73f6e1e9db59dd7d139af492c00c78
        ) {

}
