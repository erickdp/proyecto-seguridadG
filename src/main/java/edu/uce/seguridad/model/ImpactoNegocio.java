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
public class ImpactoNegocio {
    @Id
    private String _id;
    private String nombreProceso;
    private double estimacionRto;
    private double estimacionRpo;
    private double capacidadRto;
    private double capacidadPro;
    private double controlesAplicados;
    private double impacto;
    private double probabilidad;
    private double prioridad;
    private String identificacion;
    private String usuario;
}
