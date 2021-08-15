package edu.uce.seguridad.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Data
@Document
public class EvacuacionYRescate {
    @Id
    private String _id;
    private String usuario;
    @Indexed(unique = true)
    private String riesgo;
    private Map<String, String> recursos;
}
