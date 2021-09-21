package edu.uce.seguridad.repository;

import edu.uce.seguridad.model.Termino;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface TerminoRepository extends MongoRepository<Termino, String> {
    Optional<Termino> findByNombre(String nombre);
}
