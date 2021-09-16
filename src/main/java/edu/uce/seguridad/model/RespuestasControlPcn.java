package edu.uce.seguridad.model;

import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;

// Clase que forma parte de ControlPcn by Erick
@Data
public class RespuestasControlPcn {
    @Indexed(unique = true)
    private String pregunta;
    private byte medida;
    private byte respuesta;
}
