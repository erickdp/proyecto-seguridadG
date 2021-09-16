package edu.uce.seguridad;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
@Slf4j
public class ProyectoSeguridadGApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ProyectoSeguridadGApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/sgcnegocio/**")
                        .allowedOrigins("http://localhost:3000", "http://localhost:8080", "https://front-seguridades.herokuapp.com")
                        .allowedHeaders("*")
                        .allowedMethods("GET", "POST", "PUT", "DELETE")
                        .maxAge(3600);
            }
        };
    }

    @Override
    public void run(String... args) throws Exception {

//        HashMap<String, List<String>> mapa = new HashMap<>();
//
//        mapa.put("v1", Arrays.asList("E1", "E2", "E3"));
//        mapa.put("v2", Arrays.asList("E4", "E5", "E6"));
//        mapa.put("v3", Arrays.asList("E9", "E8", "E7"));
//
//        if(mapa.containsKey("v4")) {
//            log.info("No hay v4");
//        } else if (mapa.containsKey("v1")) {
//            log.info("Si hay v1");
//        }

//        mapaRecursos.forEach((miLlave, valor) -> {
//
//            mapaRecursos.get("Recursos Internos").stream().map(recurso -> {
//                estimacionDano.definirEstimacion(
//                        recurso.getNombre(),
//                        0,
//                        0,
//                        0,
//                        false);
//                return estimacionDano;
//            }).collect(Collectors.toList());
//
//
//        });
    }
}
