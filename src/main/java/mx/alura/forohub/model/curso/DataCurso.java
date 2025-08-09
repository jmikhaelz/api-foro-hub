package mx.alura.forohub.model.curso;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DataCurso(
        @NotBlank String nombre,
        @NotNull Categoria categoria) {
}