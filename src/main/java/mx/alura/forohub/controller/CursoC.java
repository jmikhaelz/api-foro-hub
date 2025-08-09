package mx.alura.forohub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import mx.alura.forohub.model.curso.Curso;
import mx.alura.forohub.model.curso.DataCurso;
import mx.alura.forohub.model.curso.DataListCurso;
import mx.alura.forohub.repository.CursoResp;

@RestController
@RequestMapping("/cursos")
@SecurityRequirement(name = "bearer-key")
public class CursoC {
    @Autowired
    private CursoResp respository;

    @Transactional
    @PostMapping
    public ResponseEntity<Object> set(@RequestBody @Valid DataCurso data, UriComponentsBuilder uri) {
        var curso = new Curso(data);
        respository.save(curso);

        var location = uri.path("/cursos/{id}")
                .buildAndExpand(curso.getId())
                .toUri();

        return ResponseEntity.created(location).body(
                new DataListCurso(
                        curso.getId(),
                        curso.getNombre(),
                        curso.getCat()));
    }
}