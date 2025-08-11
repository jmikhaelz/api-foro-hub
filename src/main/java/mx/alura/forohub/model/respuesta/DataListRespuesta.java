package mx.alura.forohub.model.respuesta;

import java.time.LocalDateTime;

public record DataListRespuesta(
                Long id,
                LocalDateTime creacion,
                String mensaje,
                String nombreTopico,
                String usuarioNombre) {
}
