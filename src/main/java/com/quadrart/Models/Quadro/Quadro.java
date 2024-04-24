package com.quadrart.Models.Quadro;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "quadros")
@NoArgsConstructor
@AllArgsConstructor
public class Quadro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome_artista")
    private String nomeArtista;

    @Column(name = "nome_album")
    private String nomeAlbum;

    @Column(name = "ano")
    private Long ano;

    @Column(name = "duracao")
    private Long duracao;

    @Column(name = "genero")
    private String genero;

    @Column(name = "imagem")
    private String imagem;

    public Quadro(RequestQuadro requestQuadro){
        this.nomeArtista = requestQuadro.nomeArtista();
        this.nomeAlbum = requestQuadro.nomeAlbum();
        this.ano = (long) (requestQuadro.ano().getValue());
        this.genero = requestQuadro.genero();
        this.duracao = requestQuadro.duracao();
        this.imagem = requestQuadro.imagem();
    }


}
