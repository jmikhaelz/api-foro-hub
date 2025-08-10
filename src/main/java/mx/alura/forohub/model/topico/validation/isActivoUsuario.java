package mx.alura.forohub.model.topico.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.alura.forohub.infra.exceptions.RulesValidationException;
import mx.alura.forohub.model.topico.DataCitarTopico;
import mx.alura.forohub.repository.UsuarioResp;

@Component
public class isActivoUsuario implements TopicoValidacion {
    @Autowired
    private UsuarioResp resp;

    @Override
    public void valid(DataCitarTopico data) {
        var status = resp.findEstatusIsActivoById(data.idAutor());
        if (!status) {
            throw new RulesValidationException("Usuario excluido no puede publicar la consulta");
        }
    }

}
