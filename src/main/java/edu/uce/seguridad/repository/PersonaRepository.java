package edu.uce.seguridad.repository;

import edu.uce.seguridad.model.Persona;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PersonaRepository extends MongoRepository<Persona, String> {
}
