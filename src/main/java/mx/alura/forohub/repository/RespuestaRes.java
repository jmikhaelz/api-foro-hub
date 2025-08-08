package mx.alura.forohub.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.alura.forohub.model.respuesta.Respuesta;

public interface RespuestaRes extends JpaRepository<Respuesta, Long> {
}
