package edu.uce.seguridad.repository;

import edu.uce.seguridad.model.BIAPregunta;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface BIAPreguntaRepository extends MongoRepository<BIAPregunta, String> {
    Optional<BIAPregunta> findByPregunta(String pregunta);
}
