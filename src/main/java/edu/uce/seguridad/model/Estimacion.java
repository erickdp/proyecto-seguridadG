package edu.uce.seguridad.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Clase que forma parte del form 4.2
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Estimacion {
    private String recurso;
    private int dano;
    private int dias;
    private int grafico;
    private boolean medidas;
}
