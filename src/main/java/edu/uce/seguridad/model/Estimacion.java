package edu.uce.seguridad.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Estimacion {
    private String tipo;
    private int dano;
    private int dias;
    private boolean medidasNecesarias;
}
