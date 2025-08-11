package mx.alura.forohub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
import mx.alura.forohub.model.topico.DataListTopico;
import mx.alura.forohub.model.topico.DataListTopicoModelAssembler;
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
    @Autowired
    private PagedResourcesAssembler<DataListTopico> pagedResourcesAssembler;
    @Autowired
    private DataListTopicoModelAssembler DataListTopicoModelAssembler;

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

    @GetMapping("")
    public ResponseEntity<PagedModel<EntityModel<DataListTopico>>> getList(
            @PageableDefault(size = 10, sort = { "titulo" }) Pageable paginacion) {
        Page<DataListTopico> pagina = resp.findAllByEstatusAbierto(paginacion).map(
                m -> new DataListTopico(m.getId(), m.getTitulo(), m.getMensaje(), m.getCreacion(),
                        m.getAutor().getNombre(),
                        m.getCurso().getNombre()));
        var page = pagedResourcesAssembler.toModel(pagina, DataListTopicoModelAssembler);
        return ResponseEntity.ok(page);
    }
}