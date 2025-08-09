package mx.alura.forohub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import mx.alura.forohub.infra.security.DataTokenJWT;
import mx.alura.forohub.infra.security.TokenService;
import mx.alura.forohub.model.usuario.DataAutowired;
import mx.alura.forohub.model.usuario.Usuario;

@RestController
@RequestMapping("/login")
public class UsuarioC {
    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<Object> initSesssion(@RequestBody @Valid DataAutowired data) {
        try {
            var token = new UsernamePasswordAuthenticationToken(data.login(), data.key());
            var authentication = manager.authenticate(token);
            var tokenJWT = tokenService.create((Usuario) authentication.getPrincipal());
            return ResponseEntity.ok(new DataTokenJWT(tokenJWT));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
