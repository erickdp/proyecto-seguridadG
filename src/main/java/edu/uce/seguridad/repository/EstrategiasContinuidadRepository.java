package edu.uce.seguridad.repository;

import edu.uce.seguridad.model.EstrategiasContinuidad;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface EstrategiasContinuidadRepository extends MongoRepository<EstrategiasContinuidad, String> {
    List<EstrategiasContinuidad> findByUsuario(String usuario);
    Optional<EstrategiasContinuidad> findByUsuarioAndActividadPrioritaria(String usuario, String actividadPrioritaria);
}
