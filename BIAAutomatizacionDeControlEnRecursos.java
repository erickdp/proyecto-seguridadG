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
public class BIAAutomatizacionDeControlEnRecursos {

    @Id
    private String _id;
    private String user;

    private String inmueble;
    private String equipos;
    private String tecnologico;
    private String humanos;
    private String primordiales;
    private String secundarios;

}
