# ForoHub API

![Build](https://img.shields.io/badge/build-passing-brightgreen) ![License](https://img.shields.io/badge/license-MIT-blue)

ForoHub es una API REST construida con **Spring Boot** para gestionar un foro de discusión, incluyendo usuarios, cursos, tópicos y respuestas.  
Este proyecto es ideal para quienes quieren aprender a crear una aplicación backend moderna y segura.

---

## Primeros pasos rápidos

1. **Clona el repositorio**
   ```sh
   git clone https://github.com/jmikhaelz/api-foro-hub
   cd forohub
   ```
2. **Configura el entorno y ejecuta Docker**
   ```sh
   cp docker/.env.example docker/.env
   docker-compose -f docker/docker-compose.yml up -d
   ```
3. **Accede a Adminer** en [http://localhost:8081](http://localhost:8081)  
   _(Usa los datos de tu `.env`)_

4. **Ejecuta la aplicación**
   ```sh
   ./mvnw spring-boot:run
   ```
5. **Explora la API en Swagger UI:**  
   [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

---

## ¿Qué resuelve este proyecto?

ForoHub facilita la creación y gestión de foros, permitiendo a los usuarios:

- Registrarse e iniciar sesión de forma segura (JWT).
- Crear y listar cursos.
- Publicar tópicos (temas de discusión).
- Responder a tópicos y marcar soluciones.
- Administrar el estado de los tópicos y respuestas.

---

## Estructura del Proyecto

```
.
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── mx/alura/forohub/   # Código fuente principal
│   │   └── resources/
│   │       ├── application.properties         # Configuración de la aplicación
│   │       └── db/migration/                  # Migraciones de base de datos (Flyway)
│   └── test/
│       └── java/                              # Pruebas unitarias y de integración
├── docker/                                    # Archivos para levantar el entorno con Docker (MySQL, Adminer)
├── .mvn/                                      # Configuración de Maven Wrapper
├── target/                                    # Archivos generados tras compilar (ignorar)
├── pom.xml                                    # Dependencias y configuración de Maven
└── README.md                                  # Este archivo
```

---

## Principales Dependencias

- **Spring Boot**: Framework principal para crear la API.
- **Spring Data JPA**: Acceso y persistencia de datos.
- **Spring Security**: Seguridad y autenticación con JWT.
- **Flyway**: Migraciones automáticas de la base de datos.
- **MySQL**: Motor de base de datos.
- **Lombok**: Reduce el código repetitivo.
- **Springdoc OpenAPI**: Documentación automática de la API.
- **HATEOAS**: Respuestas enriquecidas para APIs REST.

---

## ¿Cómo iniciar el proyecto?

1. **Clona el repositorio**

   ```sh
   git clone <url-del-repo>
   cd forohub
   ```

2. **Configura las variables de entorno**

   ```sh
   cp docker/.env.example docker/.env
   # Edita docker/.env con tus credenciales
   ```

3. **Levanta la base de datos y Adminer con Docker**

   ```sh
   docker-compose -f docker/docker-compose.yml up -d
   ```

   Esto iniciará:

   - **MySQL**: Base de datos para la aplicación.
   - **Adminer**: Interfaz web para administrar la base de datos.

4. **Accede a Adminer**

   - Ve a [http://localhost:8081](http://localhost:8081)
   - Usa los datos de tu archivo `.env` para conectarte a la base de datos.

5. **Configura `src/main/resources/application.properties`**  
   Crea este archivo y pon tus datos de conexión a MySQL.

6. **Compila y ejecuta la aplicación**

   ```sh
   ./mvnw spring-boot:run
   ```

7. **Accede a la documentación interactiva**
   - [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

---

## Tips para principiantes

- **¿Qué es un DTO?**  
  Clases que solo transportan datos entre capas.

- **¿Qué es un Repository?**  
  Interfaces para acceder a la base de datos fácilmente.

- **¿Qué es un Service?**  
  Clases donde va la lógica de negocio.

- **¿Qué es un Controller?**  
  Definen los endpoints de la API.

- **¿Qué es Flyway?**  
  Permite que la estructura de la base de datos se cree y actualice automáticamente.

- **¿Qué es JWT?**  
  Sistema de autenticación basado en tokens seguros.

- **¿Cómo pruebo la API?**  
  Usa Swagger UI, Insomnia o Postman para probar los endpoints.

---

## Tips de Base de Datos

Para crear el usuario y la base de datos en MySQL, ejecuta estos comandos (puedes hacerlo desde Adminer):

```sql
CREATE USER 'forouser'@'%' IDENTIFIED BY 'tu_contraseña_segura';
GRANT ALL PRIVILEGES ON forohub_api.* TO 'forouser'@'%';
FLUSH PRIVILEGES;
```

---

## Herramientas recomendadas

| Herramienta      | Uso principal                      | Enlace                                                                   |
| ---------------- | ---------------------------------- | ------------------------------------------------------------------------ |
| VS Code          | Editor de código                   | [Descargar](https://code.visualstudio.com/)                              |
| Docker           | Base de datos y Adminer            | [Descargar](https://www.docker.com/)                                     |
| Adminer          | Gestión visual de la base de datos | [Sitio](https://www.adminer.org/)                                        |
| Insomnia/Postman | Pruebas de endpoints               | [Insomnia](https://insomnia.rest/) / [Postman](https://www.postman.com/) |
| Swagger UI       | Documentación y pruebas rápidas    | Incluido en el proyecto                                                  |
| Git              | Control de versiones               | [Descargar](https://git-scm.com/)                                        |

---

## ¿Dónde puedo modificar la estructura de la base de datos?

- Los scripts SQL están en `src/main/resources/db/migration/`.
- Si cambias el modelo en Java, recuerda crear una nueva migración.

---

## ¿Dónde puedo aprender más?

- [Documentación de Spring Boot](https://spring.io/projects/spring-boot)
- [Documentación de Spring Security](https://spring.io/projects/spring-security)
- [Documentación de Flyway](https://flywaydb.org/documentation/)
- [Swagger/OpenAPI](https://swagger.io/tools/swagger-ui/)

---

## Créditos

- Práctica planificada por [Alura Latam](https://www.aluracursos.com/)
- Elaborado por: [jmikhaelz](https://www.linkedin.com/in/jmikhaelz/)

---
