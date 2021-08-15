package edu.uce.seguridad.repository;

import edu.uce.seguridad.model.ProteccionYMitigacion;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ProteccionYMitigacionRepository extends MongoRepository<ProteccionYMitigacion, String> {
    List<ProteccionYMitigacion> findByUsuario(String usuario);
    Optional<ProteccionYMitigacion> findByUsuarioAndRiesgo(String usuario, String riesgo);
}
