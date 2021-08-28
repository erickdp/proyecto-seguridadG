package edu.uce.seguridad.model;

import lombok.Data;

@Data
public class RecursoPrioridad {
    private String recurso;
    private String queProcede;
    private String detalleMedida;
    private String periodo;
    private String deparEncargado;
}
