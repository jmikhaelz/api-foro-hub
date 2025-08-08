package mx.alura.forohub.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.alura.forohub.model.usuario.Usuario;

public interface UsuarioResp extends JpaRepository<Usuario, Long> {

}
