package edu.uce.seguridad.repository;

import edu.uce.seguridad.model.EvacuacionYRescate;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface EvacuacionYRescateRepository extends MongoRepository<EvacuacionYRescate, String> {
    List<EvacuacionYRescate> findByUsuario(String usuario);
    Optional<EvacuacionYRescate> findByUsuarioAndRiesgo(String usuario, String riesgo);
}
