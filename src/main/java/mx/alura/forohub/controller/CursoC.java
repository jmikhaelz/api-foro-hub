package mx.alura.forohub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
import mx.alura.forohub.model.curso.DataListCursoModelAssembler;
import mx.alura.forohub.repository.CursoResp;

@RestController
@RequestMapping("/cursos")
@SecurityRequirement(name = "bearer-key")
public class CursoC {
        @Autowired
        private CursoResp repository;
        @Autowired
        private PagedResourcesAssembler<DataListCurso> pagedResourcesAssembler;
        @Autowired
        private DataListCursoModelAssembler dataListCursoModelAssembler;

        @PostMapping
        @Transactional
        public ResponseEntity<Object> set(@RequestBody @Valid DataCurso data, UriComponentsBuilder uri) {
                boolean exists = repository.existsByNombreAndCat(data.nombre(), data.categoria());
                if (exists) {
                        return ResponseEntity.status(HttpStatus.CONFLICT)
                                        .body("Error en Consulta ingresada : Ya existe el curso con la categoria.");
                }

                var curso = new Curso(data);
                repository.save(curso);

                var location = uri.path("/cursos/{id}")
                                .buildAndExpand(curso.getId())
                                .toUri();

                return ResponseEntity.created(location).body(
                                new DataListCurso(curso.getId(), curso.getNombre(), curso.getCat()));
        }

        @GetMapping("")
        public ResponseEntity<PagedModel<EntityModel<DataListCurso>>> getList(
                        @PageableDefault(page = 0, size = 10, sort = { "nombre" }) Pageable paginacion) {
                Page<DataListCurso> pagina = repository.findAll(paginacion)
                                .map(m -> new DataListCurso(m.getId(), m.getNombre(), m.getCat()));
                var page = pagedResourcesAssembler.toModel(pagina, dataListCursoModelAssembler);
                return ResponseEntity.ok(page);
        }
}