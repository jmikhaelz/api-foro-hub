package mx.alura.forohub.model.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DataAutowired(
                @NotBlank @Email String login,
                @NotBlank String key) {
}