package edu.uce.seguridad.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormularioLiderazo {
    @Id
    private String _id;
    private String pregunta;
    private List<Respuesta> respuesta;
}
