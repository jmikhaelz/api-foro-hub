package mx.alura.forohub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import mx.alura.forohub.model.respuesta.AgregarRespuesta;
import mx.alura.forohub.model.respuesta.DataEnviarRespuesta;

@RestController
@RequestMapping("/respuestas")
@SecurityRequirement(name = "bearer-key")
public class RespuestaC {
    @Autowired
    private AgregarRespuesta toolAgregar;

    @PostMapping
    @Transactional
    public ResponseEntity<Object> set(@RequestBody @Valid DataEnviarRespuesta data) {
        var publicacion = toolAgregar.publicar(data);
        return ResponseEntity.ok(publicacion);
    }
}
