package mx.alura.forohub.model.topico;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import jakarta.validation.constraints.NotNull;

@Component
public class DataListTopicoModelAssembler
        implements RepresentationModelAssembler<DataListTopico, EntityModel<DataListTopico>> {
    @SuppressWarnings("null")
    @NotNull
    @Override
    public EntityModel<DataListTopico> toModel(@NotNull DataListTopico entity) {
        return EntityModel.of(entity);
    }
}
