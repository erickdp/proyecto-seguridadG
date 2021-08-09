package edu.uce.seguridad.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

@Document
@Data
public class EstimacionDano {
    @Id
    private String _id;
    @Indexed(unique = true)
    private String usuario;
    private String riesgo;
    private String probabilidad;
    private String impacto;
    private Map<String, List<Estimacion>> recursosNecesarios;

    public Estimacion definirEstimacion(
            String tipo,
            int dano,
            int dias,
            boolean medidasNecesarias
    ) {
        return new Estimacion(tipo, dano, dias, medidasNecesarias);
    }

    @Data
    @AllArgsConstructor
    public class Estimacion {
        private String tipo;
        private int dano;
        private int dias;
        private boolean medidasNecesarias;
    }
}
