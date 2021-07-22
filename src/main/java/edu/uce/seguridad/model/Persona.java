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
public class Persona {
    @Id
    private String _id;
    private String nombre;
    private String apellido;
    private String organizacion;
    private String departamento;
    private Usuario usuario;
}
