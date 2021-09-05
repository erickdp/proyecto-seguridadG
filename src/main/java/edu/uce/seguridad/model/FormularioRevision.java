package edu.uce.seguridad.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FormularioRevision {
    private String temaRevVer;
    private String formulario;
    private String estado;
    private String cambios;
    private String temasRevisar;
}
