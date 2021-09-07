package edu.uce.seguridad.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstrategiaCN {
    private String estrategia;
    private String prioridad;
    private List<String> actividadesPrimordiales;
    private List<String> recursosPrimordiales;
    private List<String> proveedoresExternos;
}
