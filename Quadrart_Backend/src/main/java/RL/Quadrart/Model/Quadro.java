package RL.Quadrart.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "quadro")
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

    @Column(name = "genero")
    private String genero;

    @Column(name = "duracao")
    private Long duracao;

    // Construtor sem argumentos
    public Quadro() {
        // Deixe vazio ou adicione lógica padrão, se necessário
    }

    // Construtor com argumentos
    public Quadro(String nomeArtista, String nomeAlbum, Long ano, String genero, Long duracao, byte[] urlDaCapa) {
        this.nomeArtista = nomeArtista;
        this.nomeAlbum = nomeAlbum;
        this.ano = ano;
        this.genero = genero;
        this.duracao = duracao;
    }

    // Getters e Setters para todos os campos

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeArtista() {
        return nomeArtista;
    }

    public void setNomeArtista(String nomeArtista) {
        this.nomeArtista = nomeArtista;
    }

    public String getNomeAlbum() {
        return nomeAlbum;
    }

    public void setNomeAlbum(String nomeAlbum) {
        this.nomeAlbum = nomeAlbum;
    }

    public Long getAno() {
        return ano;
    }

    public void setAno(Long ano) {
        this.ano = ano;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Long getDuracao() {
        return duracao;
    }

    public void setDuracao(Long duracao) {
        this.duracao = duracao;
    }
}
