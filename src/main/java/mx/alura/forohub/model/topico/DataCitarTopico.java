package mx.alura.forohub.model.topico;

import jakarta.validation.constraints.NotNull;

public record DataCitarTopico(
        @NotNull String titulo,
        @NotNull String mensaje,
        @NotNull Long idAutor,
        @NotNull Long idCurso) {
}