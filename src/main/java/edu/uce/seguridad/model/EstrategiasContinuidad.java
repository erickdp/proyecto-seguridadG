package edu.uce.seguridad.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

@Document
@Data
public class EstrategiasContinuidad {
    @Id
    private String _id;
    private String actividadPrioritaria;
    private String usuario;
    private Map<String, List<RecursoPrioridad>> categorias;
}
