package com.quadrart.Controllers.AuthController;

import org.springframework.web.bind.annotation.RestController;

import com.quadrart.Handlers.CustomAuthenticationHandler.CustomAuthenticationProvider;
import com.quadrart.Models.Usuario.RequestUsuarioLogin;
import com.quadrart.Models.Usuario.RequestUsuarioRegistro;
import com.quadrart.Models.Usuario.Usuario;
import com.quadrart.Services.JwtService.JwtService;
import com.quadrart.Services.UsuarioService.UsuarioService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/auth")
public class AuthController {

        @Autowired
        private PasswordEncoder passwordEncoder;

        @Autowired
        private UsuarioService usuarioService;

        @Autowired
        private JwtService jwtService;

        @Autowired
        private CustomAuthenticationProvider authenticationManager;

        @CrossOrigin("*")
        @PostMapping("/login")
        @ResponseBody
        public ResponseEntity<?> loginUser(@Valid @RequestBody RequestUsuarioLogin requestUsuarioLogin,
                        HttpServletResponse response) {

                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                                requestUsuarioLogin.login(), passwordEncoder.encode(requestUsuarioLogin.senha())));

                Usuario usuario = usuarioService.loadUserByUsername(requestUsuarioLogin.login());

                String jwt = jwtService.generateToken(usuario, 72);

                ResponseCookie cookie = ResponseCookie.from("accessToken", jwt)
                                .httpOnly(true)
                                .secure(true)
                                .path("/")
                                .build();

                response.addHeader("Set-Cookie", cookie.toString());

                return ResponseEntity.ok(requestUsuarioLogin);
        }

        @PostMapping("/register")
        @ResponseBody
        public ResponseEntity<Usuario> registerUser(@Valid @RequestBody RequestUsuarioRegistro requestUsuarioRegistro,
                        HttpServletResponse response) {

                Usuario registro = new Usuario(requestUsuarioRegistro);

                registro.setSenha(passwordEncoder.encode(registro.getSenha()));

                String jwt = jwtService.generateToken(registro, 72);

                usuarioService.createUser(registro);

                ResponseCookie cookie = ResponseCookie.from("accessToken", jwt)
                                .httpOnly(true)
                                .secure(true)
                                .path("/")
                                .build();

                response.addHeader("Set-Cookie", cookie.toString());

                return ResponseEntity.ok(usuarioService.createUser(registro));

        }

        @PostMapping("/logout")
        public ResponseEntity<?> logoutUser(HttpServletResponse response) {
                String jwt = jwtService.generateToken(new Usuario(), 0);

                ResponseCookie cookie = ResponseCookie.from("accessToken", jwt)
                                .httpOnly(true)
                                .secure(true)
                                .path("/")
                                .build();
                
                response.addHeader("Set-Cookie", cookie.toString());

                return ResponseEntity.ok("Usuário deslogado com sucessso");

        }

}
