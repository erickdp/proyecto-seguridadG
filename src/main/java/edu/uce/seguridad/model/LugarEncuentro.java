package edu.uce.seguridad.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LugarEncuentro {
    private String prioridad;
    private String lugar;
    private String direccion;
    private String telefono;
}
