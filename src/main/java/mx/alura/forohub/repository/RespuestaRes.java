package mx.alura.forohub.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import mx.alura.forohub.model.respuesta.Respuesta;

public interface RespuestaRes extends JpaRepository<Respuesta, Long> {
    @EntityGraph(attributePaths = { "autor", "topico" })
    Page<Respuesta> findAllBySolucionTrue(Pageable paginacion);
}
