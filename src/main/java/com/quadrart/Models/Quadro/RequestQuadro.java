package com.quadrart.Models.Quadro;

import java.time.Year;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

public record RequestQuadro(
        @NotBlank
        @Size(min=1, max=32)
        String nomeArtista,

        @NotBlank
        @Size(min=1, max=40)
        String nomeAlbum,

        
        @NotBlank
        @PastOrPresent
        Year ano,

        @NotBlank
        @Size(min=3, max=20)
        String genero,

        @NotBlank
        @Max(999)
        Long duracao,

        @NotBlank
        String imagem
        ) {

}
