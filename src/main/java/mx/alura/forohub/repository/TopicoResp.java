package mx.alura.forohub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import mx.alura.forohub.model.topico.Topico;

public interface TopicoResp extends JpaRepository<Topico, Long> {
    @Query("SELECT t.id FROM Topico t WHERE t.titulo = :titulo")
    String existsByTituloContaining(String titulo);
}
