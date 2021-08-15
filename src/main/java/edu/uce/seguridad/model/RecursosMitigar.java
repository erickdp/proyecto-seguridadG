package edu.uce.seguridad.model;

import lombok.Data;

import java.util.List;

// Clase que forma parte del form 5.1
@Data
public class RecursosMitigar {
    private String recurso;
    private String objetivo;
    private String accion;
    private String plan;
    private List<String> plazoEstablecidoMax;
    private String departamentoEncargado;
}
