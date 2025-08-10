package mx.alura.forohub.model.topico;

public record DataAboutForo(
                Long id,
                String titulo,
                String mensaje,
                Long idAutor,
                Long idCurso) {
        public DataAboutForo(Topico data) {
                this(data.getId(), data.getTitulo(), data.getMensaje(), data.getAutor().getId(),
                                data.getCurso().getId());
        }
}