package mx.alura.forohub.model.topico;

import jakarta.validation.constraints.NotNull;

public record DataUpdateTopico(
        @NotNull Long id,
        String titulo,
        String mensaje) {
}
