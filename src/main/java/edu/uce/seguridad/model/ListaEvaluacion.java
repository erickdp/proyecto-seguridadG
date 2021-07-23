package edu.uce.seguridad.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListaEvaluacion {

    @Id
    private String _id;
    private String user;

    private String tipoCalidad;
    private String tipo;
    private String caracteristica;
}
