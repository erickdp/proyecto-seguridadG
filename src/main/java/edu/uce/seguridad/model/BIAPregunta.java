package edu.uce.seguridad.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

// By Erick
@Data
@Document
public class BIAPregunta {
    @Id
    private String _id;
    @Indexed(unique = true)
    private String pregunta;
}
