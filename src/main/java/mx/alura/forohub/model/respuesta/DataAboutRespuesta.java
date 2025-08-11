package mx.alura.forohub.model.respuesta;

public record DataAboutRespuesta(
        Long id,
        String mensaje,
        Long idTopico,
        Long idAutor) {

    public DataAboutRespuesta(Respuesta data) {
        this(data.getId(), data.getMensaje(), data.getTopico().getId(), data.getAutor().getId());
    }
}
