package edu.uce.seguridad.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

// Clase que maneja diferentes terminos y definiciones - Erick
@Data
@Document
public class Termino {
    @Id
    private String _id;
    @Indexed(unique = true)
    private String nombre;
    private String definicion;
}
