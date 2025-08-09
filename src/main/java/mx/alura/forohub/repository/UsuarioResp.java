package mx.alura.forohub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import mx.alura.forohub.model.usuario.Usuario;

@Repository
public interface UsuarioResp extends JpaRepository<Usuario, Long> {
    UserDetails findByEmail(String email);
}
