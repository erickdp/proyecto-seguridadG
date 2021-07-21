package edu.uce.seguridad.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormularioEvaluacionDanos {
    @Id
    private String _id;
    private String tipoLocalidad;
    private Respuesta respuesta;
}
