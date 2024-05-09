package com.quadrart.Models.Quadro;

<<<<<<< HEAD
import java.util.ArrayList;
import java.util.List;

import com.quadrart.Models.Usuario.Usuario;

=======
>>>>>>> a8e55b201a73f6e1e9db59dd7d139af492c00c78
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
<<<<<<< HEAD
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * Classe que representa um quadro na base de dados.
 * @Data cria setters e getters automaticamente para todos os parametros
 */
=======
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

>>>>>>> a8e55b201a73f6e1e9db59dd7d139af492c00c78
@Data
@Entity
@Table(name = "quadros")
@NoArgsConstructor
<<<<<<< HEAD
=======
@AllArgsConstructor
>>>>>>> a8e55b201a73f6e1e9db59dd7d139af492c00c78
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
<<<<<<< HEAD
    private long ano;
=======
    private Long ano;
>>>>>>> a8e55b201a73f6e1e9db59dd7d139af492c00c78

    @Column(name = "duracao")
    private Long duracao;

    @Column(name = "genero")
    private String genero;

    @Column(name = "imagem")
    private String imagem;

    public Quadro(RequestQuadro requestQuadro){
        this.nomeArtista = requestQuadro.nomeArtista();
        this.nomeAlbum = requestQuadro.nomeAlbum();
<<<<<<< HEAD
        this.ano = requestQuadro.ano();
        this.genero = requestQuadro.genero();
        this.duracao = requestQuadro.duracao();
        this.imagem = "";
=======
        this.ano = (long) (requestQuadro.ano().getValue());
        this.genero = requestQuadro.genero();
        this.duracao = requestQuadro.duracao();
        this.imagem = requestQuadro.imagem();
>>>>>>> a8e55b201a73f6e1e9db59dd7d139af492c00c78
    }


}
