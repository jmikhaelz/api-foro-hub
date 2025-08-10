package mx.alura.forohub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import mx.alura.forohub.model.topico.AgregarConsulta;
import mx.alura.forohub.model.topico.DataAboutForo;
import mx.alura.forohub.model.topico.DataCitarTopico;
import mx.alura.forohub.model.topico.DataUpdateTopico;
import mx.alura.forohub.model.topico.Estatus;
import mx.alura.forohub.repository.TopicoResp;

@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicoC {
    @Autowired
    private AgregarConsulta toolAgregar;
    @Autowired
    private TopicoResp resp;

    @PostMapping
    @Transactional
    public ResponseEntity<Object> set(@RequestBody @Valid DataCitarTopico data, UriComponentsBuilder uri) {
        var citado = toolAgregar.citar(data);
        return ResponseEntity.ok(citado);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void desactivar(@PathVariable Long id) {
        var topico = resp.getReferenceById(id);
        topico.changEstatus(Estatus.cerrado);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<Object> actualizar(@RequestBody @Valid DataUpdateTopico data) {
        var topico = resp.getReferenceById(data.id());
        topico.actualizar(data);
        return ResponseEntity.ok(
                new DataAboutForo(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getAutor().getId(),
                        topico.getCurso().getId()));
    }
}