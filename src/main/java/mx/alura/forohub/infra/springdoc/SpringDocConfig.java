package mx.alura.forohub.infra.springdoc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SpringDocConfig {
    @Bean
    public OpenAPI cusOpenAPI() {
        return new OpenAPI().components(
                new Components().addSecuritySchemes("bearer-key",
                        new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")))
                .info(new Info().title("Foro.Hub_Api").description(
                        "API REST de la aplicacion de control de FORO HUB, que contiene funciones de manejos de usuarios, topicos,cursos y respuestas de los mismos")
                        .contact(new Contact().name("@jmikhaelz").email("jesus.miguelz@outlook.com"))
                        .license(new License().name("Apache 2.0")));
    }
}
