package edu.uce.seguridad.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Recurso {
    @Id
    private String _id;
    private String tipoRecurso;
    private String recurso;
    private String nombre;
    private String contenido;
    private Usuario usuario;
}
