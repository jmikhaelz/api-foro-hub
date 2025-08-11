package mx.alura.forohub.model.topico;

import java.time.LocalDateTime;

public record DataListTopico(
    Long id,
    String titulo,
    String mensaje,
    LocalDateTime creacion,
    String usuario,
    String curso
) {}
