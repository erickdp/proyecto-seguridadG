package edu.uce.seguridad.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@AllArgsConstructor
@NoArgsConstructor
public class Estrategia {

    private String nombreEstrategia;

    private String prioridad;
    private String resumenEstrategia;
    private String actividadesReanudar;
    private String recursosClaves;
    private String sociosExternos;


}
