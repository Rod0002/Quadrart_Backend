package RL.Quadrart.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import RL.Quadrart.Model.Usuario;
import RL.Quadrart.Service.UserServiceImpl;
import RL.Quadrart.Service.UsuarioService;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private UserServiceImpl userDetailsService;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    private final UsuarioService userService;


    public AuthController(UsuarioService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody Usuario user) {
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());

            String password = user.getPassword();
            String dbPassword = userDetails.getPassword();

            if (!passwordEncoder.matches(password, dbPassword)) {
                System.out.println(password);
                System.out.println(dbPassword);
                throw new BadCredentialsException("Invalid username or password");
            }
            
            var token = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
            var authentication = manager.authenticate(token);

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Usuario user) {
        // Antes de salvar no banco de dados, criptografe a senha
        userService.registerUser(user);
        return new ResponseEntity<>("Usuário registrado com sucesso.", HttpStatus.CREATED);
    }
}