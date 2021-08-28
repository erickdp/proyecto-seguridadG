package edu.uce.seguridad.model;

import lombok.Data;

@Data
public class BIAAnalisisImpactoNegocioDetalle {
    private String nombreProceso;
    private Byte estimacionRTO;
    private Byte estimacionRPO;
    private Byte capacidadSistemasRTO;
    private Byte capacidadRPO;
    private String controlesAplicados;
    private Byte impacto;
    private Byte probabilidad;
    private Byte prioridad;
    private boolean procesoCritico;
}
