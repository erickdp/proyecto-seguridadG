package edu.uce.seguridad.service.Imp;

import edu.uce.seguridad.model.Estimacion;
import edu.uce.seguridad.model.EstimacionDano;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class EstimacionDanoFactory {
    @Bean
    public EstimacionDano getEstimacionDano() {
        EstimacionDano estimacionDano = new EstimacionDano();

        estimacionDano.setImpacto("A");
        estimacionDano.setProbabilidad("A");
        estimacionDano.setRiesgo("SISMO");
        estimacionDano.setUsuario("tsegud31");

        HashMap<String, List<Estimacion>> estimaciones = new HashMap<>();

        estimaciones.put("Recursos Internos", Arrays.asList(
                estimacionDano.definirEstimacion("Inmuebles", 3, 7, true),
                estimacionDano.definirEstimacion("Equipos", 3, 7, true)
        ));

        estimaciones.put("Servicios Sociales Escenciales", Arrays.asList(
                estimacionDano.definirEstimacion("Electricidad", 2, 30, true),
                estimacionDano.definirEstimacion("Transito", 2, 9, false)
        ));

        estimaciones.put("Socios de Negocios", Arrays.asList(
                estimacionDano.definirEstimacion("Socios", 1, 8, true),
                estimacionDano.definirEstimacion("Proveedores", 2, 5, false)
        ));

        estimacionDano.setRecursosNecesarios(estimaciones);

        return estimacionDano;
    }

    @Bean
    public Map<String, List<Estimacion>> recursosNecesarios() {
        EstimacionDano estimacionDano = new EstimacionDano();
        HashMap<String, List<Estimacion>> mapa = new HashMap<>();
        mapa.put("ABC", Arrays.asList(
                estimacionDano.definirEstimacion("Inmuebles", 3, 7, true),
                estimacionDano.definirEstimacion("Electricidad", 3, 7, true))
        );
        return mapa;
    }
}
