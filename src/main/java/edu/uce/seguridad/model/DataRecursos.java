package edu.uce.seguridad.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DataRecursos {

    private DataAmenazas amenazas;
    private int rpo;
    private int rto;

}
