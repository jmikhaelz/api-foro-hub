package mx.alura.forohub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import mx.alura.forohub.model.curso.Categoria;
import mx.alura.forohub.model.curso.Curso;

public interface CursoResp extends JpaRepository<Curso, Long> {
    boolean existsByNombreAndCat(String nombre, Categoria cat);
}
