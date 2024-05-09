package com.quadrart.Config;

<<<<<<< HEAD
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
=======

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
>>>>>>> a8e55b201a73f6e1e9db59dd7d139af492c00c78
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
<<<<<<< HEAD
import com.quadrart.Handlers.CustomAuthenticationProvider;
=======

import com.quadrart.Handlers.CustomAuthenticationHandler.CustomAuthenticationProvider;
import com.quadrart.Services.UsuarioService.UsuarioService;
>>>>>>> a8e55b201a73f6e1e9db59dd7d139af492c00c78

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

<<<<<<< HEAD
=======
    private final UsuarioService userService;
>>>>>>> a8e55b201a73f6e1e9db59dd7d139af492c00c78
    private final JwtAuthenticationFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        /*
         * Através do encadeamento de funções no parametro http, é possível definitir as
<<<<<<< HEAD
         * configurações de segurança do @Bean, como quais requisições serão permitidas,
         * métodos permitidos por caminho, csrf, e etc.
=======
         * configurações de segurança do @Bean
>>>>>>> a8e55b201a73f6e1e9db59dd7d139af492c00c78
         */
        http
                .cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
<<<<<<< HEAD
                .authorizeHttpRequests(request -> request.requestMatchers("/auth/login", "/auth/register", "/auth/logout", "/auth/existcheck", "/auth/checkTokenExp")
=======
                .authorizeHttpRequests(request -> request.requestMatchers("/auth/login", "/auth/register", "/auth/logout")
>>>>>>> a8e55b201a73f6e1e9db59dd7d139af492c00c78
                        .permitAll()
                        .requestMatchers(HttpMethod.GET, "/quadro", "/quadro/*", "quadro/image/*")
                        .permitAll()
                        .anyRequest()
                        .authenticated())
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    /*
     * Cria um @Bean que retorna um objeto PasswordEncoder, que permite a utilização
     * do mesmo em locais que o encoding é nessário.
     */
<<<<<<< HEAD

=======
>>>>>>> a8e55b201a73f6e1e9db59dd7d139af492c00c78
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

<<<<<<< HEAD
    /*
     * Cria um @Bean que retorna um objeto CustomAuthenticationProvider, que permite 
     * a verificação customizada de login. No caso, o login pode ser feito com username ou email;
     */

=======
>>>>>>> a8e55b201a73f6e1e9db59dd7d139af492c00c78
    @Bean
    public CustomAuthenticationProvider authenticationManager() throws Exception {
        return new CustomAuthenticationProvider();
    }

}
