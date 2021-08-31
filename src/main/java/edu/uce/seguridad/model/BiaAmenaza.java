package edu.uce.seguridad.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BiaAmenaza {

    private String tipoAmenaza;
    private String escenario;
    private String descripcion;

}
