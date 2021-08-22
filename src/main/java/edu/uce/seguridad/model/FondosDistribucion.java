package edu.uce.seguridad.model;

import lombok.Data;

import java.math.BigDecimal;

// Clase que forma parte de FondosDisponibles.class
@Data
public class FondosDistribucion {
    private String tipo;
    private BigDecimal monto;
    private String otros;
}
