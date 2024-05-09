package com.quadrart.Models.Usuario;

<<<<<<< HEAD
import java.util.ArrayList;
=======
>>>>>>> a8e55b201a73f6e1e9db59dd7d139af492c00c78
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

<<<<<<< HEAD
import com.quadrart.Models.Quadro.Quadro;

=======
>>>>>>> a8e55b201a73f6e1e9db59dd7d139af492c00c78
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
<<<<<<< HEAD
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
=======
>>>>>>> a8e55b201a73f6e1e9db59dd7d139af492c00c78
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "usuarios")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails {

    public Usuario(RequestUsuarioRegistro usuario){
        this.nome = usuario.nome();
        this.username = usuario.username();
        this.email = usuario.email();
        this.senha = usuario.senha();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="nome")
    private String nome;

    @Column(name="username", unique = true)
    private String username;

    @Column(name="email", unique = true)
    private String email;

    @Column(name="senha")
    private String senha;

<<<<<<< HEAD
    @OneToMany
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private List<Quadro> quadrosCriados = new ArrayList<Quadro>();

    public void addQuadrosCriados(Quadro quadro) {
        quadrosCriados.add(quadro);
    }
    public List<Quadro> getQuadrosCriados(){
        return this.quadrosCriados;
    }
=======
>>>>>>> a8e55b201a73f6e1e9db59dd7d139af492c00c78

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
