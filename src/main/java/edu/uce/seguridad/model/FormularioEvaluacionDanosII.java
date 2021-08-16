package edu.uce.seguridad.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.List;

@Data
@Document
@NoArgsConstructor
@AllArgsConstructor
public class FormularioEvaluacionDanosII {

    @Id
    private String _id;

    private String usuario;
    private HashMap<String, List<RecursoDataII>> recursos;
}
