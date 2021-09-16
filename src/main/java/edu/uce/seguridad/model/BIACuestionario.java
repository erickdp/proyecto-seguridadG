package edu.uce.seguridad.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document
public class BIACuestionario {
    @Id
    private String _id;
    @Indexed(unique = true)
    private String usuario;
    private List<Byte> respuestas;
}
