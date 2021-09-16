package edu.uce.seguridad.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DataAmenazas {

    private String amenaza;
    private int probabilidad;
    private int impacto;
    private int severidad;

}
