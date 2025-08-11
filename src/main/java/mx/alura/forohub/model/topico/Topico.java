package mx.alura.forohub.model.topico;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mx.alura.forohub.infra.exceptions.RulesValidationException;
import mx.alura.forohub.model.curso.Curso;
import mx.alura.forohub.model.usuario.Usuario;

@Table(name = "topico")
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 255, nullable = false)
    private String titulo;
    private String mensaje;

    @Enumerated(EnumType.STRING)
    private Estatus estatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id")
    private Usuario autor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id")
    private Curso curso;

    @CreationTimestamp
    @Column(name = "creacion", nullable = false, updatable = false)
    private LocalDateTime creacion;

    public void changEstatus(Estatus es) {
        this.estatus = es;
    }

    public void actualizar(DataUpdateTopico data) {
        if (data == null) {
            throw new RulesValidationException("Solicitud vacia");
        }
        if (data.titulo() != null && !data.titulo().isBlank()) {
            this.titulo = data.titulo();
        }
        if (data.mensaje() != null && !data.mensaje().isBlank()) {
            this.mensaje = data.mensaje();
        }
    }
}