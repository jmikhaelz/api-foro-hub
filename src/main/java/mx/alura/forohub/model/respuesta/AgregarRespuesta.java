package mx.alura.forohub.model.respuesta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.alura.forohub.infra.exceptions.RulesValidationException;
import mx.alura.forohub.model.topico.Topico;
import mx.alura.forohub.model.usuario.Usuario;
import mx.alura.forohub.repository.RespuestaRes;
import mx.alura.forohub.repository.TopicoResp;
import mx.alura.forohub.repository.UsuarioResp;

@Service
public class AgregarRespuesta {
    @Autowired
    private RespuestaRes resp;
    @Autowired
    private TopicoResp topicoResp;
    @Autowired
    private UsuarioResp usuarioResp;

    public DataAboutRespuesta publicar(DataEnviarRespuesta data) {
        var usuario = buscarUsuario(data);
        var topico = buscarTopico(data);

        var publicacion = new Respuesta(
                null, data.mensaje(), topico, usuario, null, null);

        resp.save(publicacion);

        return new DataAboutRespuesta(publicacion);
    }

    private Topico buscarTopico(DataEnviarRespuesta data) {
        if (!topicoResp.existsById(data.idTopico())) {
            throw new RulesValidationException("No existe el ID del topico ingresado");
        }
        return topicoResp.getReferenceById(data.idTopico());
    }

    private Usuario buscarUsuario(DataEnviarRespuesta data) {
        if (!(usuarioResp.existsById(data.idAutor()))) {
            throw new RulesValidationException("No existe el ID del usuario ingresado");
        }
        return usuarioResp.getReferenceById(data.idAutor());
    }

}
