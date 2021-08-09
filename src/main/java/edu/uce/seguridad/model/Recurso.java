package edu.uce.seguridad.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Recurso {
    @Id
    private String _id;
    private String tipoRecurso;
    private String nombre;
    private String contenido;
    private Usuario usuario;
}
