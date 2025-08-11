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

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import mx.alura.forohub.model.respuesta.AgregarRespuesta;
import mx.alura.forohub.model.respuesta.DataAboutRespuesta;
import mx.alura.forohub.model.respuesta.DataEnviarRespuesta;
import mx.alura.forohub.model.respuesta.DataListRespuesta;
import mx.alura.forohub.model.respuesta.DataListRespuestaModelAssembler;
import mx.alura.forohub.model.respuesta.DataUpdateRespuesta;
import mx.alura.forohub.repository.RespuestaRes;

@RestController
@RequestMapping("/respuestas")
@SecurityRequirement(name = "bearer-key")
public class RespuestaC {
    @Autowired
    private AgregarRespuesta toolAgregar;
    @Autowired
    private RespuestaRes resp;
    @Autowired
    private PagedResourcesAssembler<DataListRespuesta> pagedResourcesAssembler;
    @Autowired
    private DataListRespuestaModelAssembler dataListRespuestaModelAssembler;

    @PostMapping
    @Transactional
    public ResponseEntity<Object> set(@RequestBody @Valid DataEnviarRespuesta data) {
        var publicacion = toolAgregar.publicar(data);
        return ResponseEntity.ok(publicacion);
    }

    @GetMapping("")
    public ResponseEntity<PagedModel<EntityModel<DataListRespuesta>>> getList(
            @PageableDefault(size = 10, sort = { "creacion" }) Pageable paginacion) {
        Page<DataListRespuesta> pagina = resp.findAllBySolucionTrue(paginacion).map(
                m -> new DataListRespuesta(
                        m.getId(), m.getCreacion(), m.getMensaje(), m.getTopico().getTitulo(),
                        m.getAutor().getNombre()));
        var page = pagedResourcesAssembler.toModel(pagina, dataListRespuestaModelAssembler);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<Object> actualizar(@RequestBody @Valid DataUpdateRespuesta data) {
        var respuesta = resp.getReferenceById(data.id());
        respuesta.changeMsg(data.mensaje());

        return ResponseEntity.ok(
                new DataAboutRespuesta(respuesta));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> eliminar(@PathVariable Long id) {
        if (!resp.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        resp.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}