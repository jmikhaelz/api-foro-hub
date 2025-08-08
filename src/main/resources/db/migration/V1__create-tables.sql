CREATE TABLE curso (
  id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  nombre VARCHAR(100)    NOT NULL,
  categoria VARCHAR(100) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE usuario (
  id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  nombre VARCHAR(100)    NOT NULL,
  email VARCHAR(100)     NOT NULL UNIQUE,
  psw VARCHAR(255)       NOT NULL,
  estatus TINYINT(1)     NOT NULL DEFAULT 1,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE topico (
  id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  titulo VARCHAR(255)     NOT NULL,
  mensaje TEXT            NOT NULL,
  creacion TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP,
  estatus VARCHAR(50)     NOT NULL DEFAULT 'abierto',
  autor_id INT UNSIGNED   NOT NULL,
  curso_id INT UNSIGNED   NOT NULL,
  PRIMARY KEY (id),
  KEY idx_topico_autor (autor_id),
  KEY idx_topico_curso (curso_id),
  CONSTRAINT fk_topico_usuario FOREIGN KEY (autor_id)
    REFERENCES usuario (id)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT fk_topico_curso FOREIGN KEY (curso_id)
    REFERENCES curso (id)
    ON DELETE RESTRICT
    ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE respuesta (
  id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  mensaje TEXT           NOT NULL,
  topico_id INT UNSIGNED NOT NULL,
  autor_id INT UNSIGNED   NOT NULL,
  solucion TINYINT(1)     NOT NULL DEFAULT 0,
  creacion TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  KEY idx_respuesta_topico (topico_id),
  KEY idx_respuesta_autor (autor_id),
  CONSTRAINT fk_resp_topico FOREIGN KEY (topico_id)
    REFERENCES topico (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT fk_resp_usuario FOREIGN KEY (autor_id)
    REFERENCES usuario (id)
    ON DELETE RESTRICT
    ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
