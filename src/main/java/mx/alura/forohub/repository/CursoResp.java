package mx.alura.forohub.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.alura.forohub.model.curso.Curso;

public interface CursoResp extends JpaRepository<Curso, Long> {

}
