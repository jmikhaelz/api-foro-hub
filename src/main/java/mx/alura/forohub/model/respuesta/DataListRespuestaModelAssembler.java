package mx.alura.forohub.model.respuesta;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import jakarta.validation.constraints.NotNull;

@Component
public class DataListRespuestaModelAssembler
        implements RepresentationModelAssembler<DataListRespuesta, EntityModel<DataListRespuesta>> {
    @SuppressWarnings("null")
    @NotNull
    @Override
    public EntityModel<DataListRespuesta> toModel(DataListRespuesta entity) {
        return EntityModel.of(entity);
    }

}
