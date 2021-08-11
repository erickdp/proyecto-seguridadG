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
public class PeriodoTolerable {
    @Id
    private String _id;
    private String producto;
    private String pmti;
    private String tiempoIdealRecuperacion;
    private Usuario usuario;
}
