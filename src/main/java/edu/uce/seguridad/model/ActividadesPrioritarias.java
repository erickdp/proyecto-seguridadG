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
public class ActividadesPrioritarias {
    @Id
    private String _id;
    private String actividades;
    private String tiemposRecuperacion;
    private Usuario usuario;
}
