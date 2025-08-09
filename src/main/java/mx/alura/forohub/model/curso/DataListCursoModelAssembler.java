package mx.alura.forohub.model.curso;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import jakarta.validation.constraints.NotNull;

@Component
public class DataListCursoModelAssembler
        implements RepresentationModelAssembler<DataListCurso, EntityModel<DataListCurso>> {

    @SuppressWarnings("null")
    @NotNull
    @Override
    public EntityModel<DataListCurso> toModel(@NotNull DataListCurso entity) {
        return EntityModel.of(entity);
    }

}
