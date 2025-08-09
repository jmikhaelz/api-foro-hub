package mx.alura.forohub.infra.exceptions;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class MannagerErrors {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> errorHandler404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<DataErrorValid>> errorHandlerBlank(MethodArgumentNotValidException ex) {
        var errores = ex.getFieldErrors().stream()
                .map(DataErrorValid::new)
                .toList();
        return ResponseEntity.badRequest().body(errores);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> gestionarError400(HttpMessageNotReadableException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Object> gestionarErrorBadCredentials() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Object> gestionarErrorAuthentication() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Falla en la autenticación");
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Object> gestionarErrorAccesoDenegado() {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Acceso denegado");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> gestionarError500(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + ex.getLocalizedMessage());
    }

    @ExceptionHandler(RulesValidationException.class)
    public ResponseEntity<Object> gestionarErrorValid(Exception ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    public record DataErrorValid(
            String campo,
            String mensaje) {
        public DataErrorValid(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
