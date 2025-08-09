package mx.alura.forohub.infra.exceptions;

public class RulesValidationException extends RuntimeException{
    public RulesValidationException(String ex){
        super(" [FORO HUB] "+ex);
    }
}
