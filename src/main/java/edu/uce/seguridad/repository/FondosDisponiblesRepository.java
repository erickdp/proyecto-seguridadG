package edu.uce.seguridad.repository;

import edu.uce.seguridad.model.FondosDisponibles;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface FondosDisponiblesRepository extends MongoRepository<FondosDisponibles, String> {
    Optional<FondosDisponibles> findByUsaurio(String usuario);
}
