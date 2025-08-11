package mx.alura.forohub.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import mx.alura.forohub.model.topico.Topico;

public interface TopicoResp extends JpaRepository<Topico, Long> {

    @EntityGraph(attributePaths = { "autor", "curso" })
    @Query("SELECT t FROM Topico t WHERE t.estatus = 'abierto'")
    Page<Topico> findAllByEstatusAbierto(Pageable paginacion);

    @Query("SELECT t.id FROM Topico t WHERE t.titulo = :titulo")
    String existsByTituloContaining(String titulo);
}
