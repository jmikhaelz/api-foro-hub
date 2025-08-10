package mx.alura.forohub.model.topico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.alura.forohub.infra.exceptions.RulesValidationException;
import mx.alura.forohub.model.curso.Curso;
import mx.alura.forohub.model.topico.validation.TopicoValidacion;
import mx.alura.forohub.model.usuario.Usuario;
import mx.alura.forohub.repository.CursoResp;
import mx.alura.forohub.repository.TopicoResp;
import mx.alura.forohub.repository.UsuarioResp;

@Service
public class AgregarConsulta {
    @Autowired
    private UsuarioResp usuarioResp;
    @Autowired
    private CursoResp cursoResp;
    @Autowired
    private TopicoResp topicoResp;
    @Autowired
    private List<TopicoValidacion> validCheckup;

    public DataAboutForo citar(DataCitarTopico data) {
        var exist = topicoResp.existsByTituloContaining(data.titulo());

        if (exist != null) {
            throw new RulesValidationException("Ya existe la consulta en el Foro, con el ID: " + exist);
        }
        var usuario = buscarUsuario(data.idAutor());
        var curso = buscarCurso(data.idCurso());

        validCheckup.forEach(v -> v.valid(data));

        var consulta = new Topico(
                null, data.titulo(), data.mensaje(), Estatus.abierto, usuario, curso);

        topicoResp.save(consulta);

        return new DataAboutForo(consulta);
    }

    private Curso buscarCurso(Long idCurso) {
        if (!(cursoResp.existsById(idCurso))) {
            throw new RulesValidationException("No existe el ID del curso ingresado");
        }
        return cursoResp.getReferenceById(idCurso);
    }

    private Usuario buscarUsuario(Long idAutor) {
        if (!(usuarioResp.existsById(idAutor))) {
            throw new RulesValidationException("No existe el ID del usuario ingresado");
        }
        return usuarioResp.getReferenceById(idAutor);
    }

}
