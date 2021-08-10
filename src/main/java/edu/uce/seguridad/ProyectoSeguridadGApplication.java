package edu.uce.seguridad;

import edu.uce.seguridad.model.Recurso;
import edu.uce.seguridad.service.Imp.EstimacionDanoFactory;
import edu.uce.seguridad.service.Imp.EstimacionDanoServiceImp;
import edu.uce.seguridad.service.Imp.RecursoServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@SpringBootApplication
public class ProyectoSeguridadGApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProyectoSeguridadGApplication.class, args);
    }

//    @Bean
//    CommandLineRunner runner(EstimacionDanoFactory data, EstimacionDanoServiceImp service, RecursoServiceImpl recursoService) {
//        return args -> {
////            service.agregar(data.getEstimacionDano());
//
//            List<Recurso> recursos = recursoService.buscarTodos();
//            Recurso recur = recursos.get(0);
//
//            recursos.forEach( re -> System.out.println(re.getRecursos().get("Recursos Internos").get(0).getNombre()));
//
//            System.out.println(recur.getRecursos().get("Servicios sociales escenciales"));
//
//        };
//    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/sgcnegocio/**")
                        .allowedOrigins("http://localhost:3000", "http://localhost:8080")
                        .allowedHeaders("*")
                        .allowedMethods("GET", "POST", "PUT", "DELETE")
                        .maxAge(3600);
            }
        };
    }

}
