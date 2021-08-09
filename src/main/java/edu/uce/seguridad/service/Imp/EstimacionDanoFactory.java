package edu.uce.seguridad.service.Imp;

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
        estimacionDano.setUsuario("erickdp");

        HashMap<String, List<EstimacionDano.Estimacion>> estimaciones = new HashMap<>();

        estimaciones.put("recursosInternos", Arrays.asList(
                estimacionDano.definirEstimacion("Inmuebles", 3, 7, true),
                estimacionDano.definirEstimacion("Electricidad", 3, 7, true)
        ));

        estimaciones.put("serviciosSocialesEscenciales", Arrays.asList(
                estimacionDano.definirEstimacion("Electricidad", 2, 30, true),
                estimacionDano.definirEstimacion("Transito", 2, 9, false)
        ));

        estimacionDano.setRecursosNecesarios(estimaciones);

        return estimacionDano;
    }

    @Bean
    public Map<String, List<EstimacionDano.Estimacion>> recursosNecesarios() {
        EstimacionDano estimacionDano = new EstimacionDano();
        HashMap<String, List<EstimacionDano.Estimacion>> mapa = new HashMap<>();
        mapa.put("ABC", Arrays.asList(
                estimacionDano.definirEstimacion("Inmuebles", 3, 7, true),
                estimacionDano.definirEstimacion("Electricidad", 3, 7, true))
        );
        return mapa;
    }
}
