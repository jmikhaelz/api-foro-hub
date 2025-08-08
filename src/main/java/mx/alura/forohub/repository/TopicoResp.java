package mx.alura.forohub.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.alura.forohub.model.topico.Topico;

public interface TopicoResp extends JpaRepository<Topico, Long> {

}
