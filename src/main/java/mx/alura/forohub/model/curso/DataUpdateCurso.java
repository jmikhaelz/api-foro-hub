package mx.alura.forohub.model.curso;

import jakarta.validation.constraints.NotNull;

public record DataUpdateCurso(
    @NotNull Long id,
    String nombre,
    Categoria categoria
) {}
