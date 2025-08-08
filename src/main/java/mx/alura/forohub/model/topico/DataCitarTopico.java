package mx.alura.forohub.model.topico;

import jakarta.validation.constraints.NotNull;

public record DataCitarTopico(
        Long idTopico,
        @NotNull String titulo,
        @NotNull String mensaje,
        Estatus estatus,
        @NotNull Long idAutor,
        @NotNull Long idCurso) {
}
