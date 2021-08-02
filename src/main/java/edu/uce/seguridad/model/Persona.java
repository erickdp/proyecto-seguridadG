package edu.uce.seguridad.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document
public class Persona {
    @Id
    private String _id;
    private String nombre;
    private String apellido;
    private String organizacion;
    private String departamento;
    private Usuario usuario;
}
