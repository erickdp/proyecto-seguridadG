package edu.uce.seguridad.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FormularioMedidasFinancieras {
    @Id
    private String _id;

    private String user;
    private String medidasFinancieras;
    private double monto;
    private String detalles;
}
