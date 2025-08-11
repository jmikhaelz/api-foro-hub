package mx.alura.forohub.model.respuesta;

import jakarta.validation.constraints.NotNull;

public record DataEnviarRespuesta(
        @NotNull String mensaje,
        @NotNull Long idTopico,
        @NotNull Long idAutor) {
}
