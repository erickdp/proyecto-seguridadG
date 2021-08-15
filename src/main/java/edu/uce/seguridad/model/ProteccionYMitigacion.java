package edu.uce.seguridad.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Data
public class ProteccionYMitigacion {
    @Id
    private String _id;
    private String usuario;
    private String riesgo;
    private List<RecursosMitigar> recursosMitigar;
}
